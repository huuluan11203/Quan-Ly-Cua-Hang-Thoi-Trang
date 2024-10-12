package com.ShopManager.identity_service.service;

import java.util.HashSet;
import java.util.List;

import com.ShopManager.identity_service.DTO.request.AccountCreationRequest;
import com.ShopManager.identity_service.DTO.request.UserCreationRequest;
import com.ShopManager.identity_service.DTO.response.AccountResponse;
import com.ShopManager.identity_service.client.UserClient;
import com.ShopManager.identity_service.entity.Account;
import com.ShopManager.identity_service.mapper.UserMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ShopManager.identity_service.constant.PredefinedRole;
import com.ShopManager.identity_service.DTO.request.AccountUpdateRequest;
import com.ShopManager.identity_service.entity.Role;
import com.ShopManager.identity_service.exception.AppException;
import com.ShopManager.identity_service.exception.ErrorCode;
import com.ShopManager.identity_service.mapper.AccountMapper;
import com.ShopManager.identity_service.repository.RoleRepository;
import com.ShopManager.identity_service.repository.AccountRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccountService {
    AccountRepository accountRepository;
    RoleRepository roleRepository;
    AccountMapper accountMapper;
    PasswordEncoder passwordEncoder;
    UserClient userClient;
    UserMapper userMapper;

    public AccountResponse createAccount(AccountCreationRequest request) {
        if (accountRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        Account account = accountMapper.toAccount(request);
        account.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<Role> roles = new HashSet<>();
        roleRepository.findById(PredefinedRole.USER_ROLE).ifPresent(roles::add);
        account.setRoles(roles);

        UserCreationRequest userCreationRequest = userMapper.toUserCreationRequest(request);

        try {
            account = accountRepository.save(account);
            userCreationRequest.setId(account.getId());

            userClient.createUser(userCreationRequest);
        } catch (Exception ex) {
            accountRepository.deleteById(account.getId());
            throw new AppException(ErrorCode.USER_CREATION_FAILED);
        }

        return accountMapper.toAccountResponse(account);
    }

    public AccountResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        Account account = accountRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return accountMapper.toAccountResponse(account);
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public AccountResponse updateAccount(String userId, AccountUpdateRequest request) {
        Account account = accountRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        accountMapper.updateAccount(account, request);
        account.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = roleRepository.findAllById(request.getRoles());
        account.setRoles(new HashSet<>(roles));

        return accountMapper.toAccountResponse(accountRepository.save(account));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAccount(String userId) {
        accountRepository.deleteById(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<AccountResponse> getAccounts() {
        log.info("In method get Users");
        return accountRepository.findAll().stream().map(accountMapper::toAccountResponse).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public AccountResponse getAccount(String id) {
        return accountMapper.toAccountResponse(
                accountRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }
}
