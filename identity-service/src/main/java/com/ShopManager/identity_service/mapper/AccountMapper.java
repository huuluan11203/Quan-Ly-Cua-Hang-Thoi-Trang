package com.ShopManager.identity_service.mapper;

import com.ShopManager.identity_service.DTO.request.AccountCreationRequest;
import com.ShopManager.identity_service.DTO.request.AccountUpdateRequest;
import com.ShopManager.identity_service.DTO.response.AccountResponse;
import com.ShopManager.identity_service.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "username", source = "request.username")
    @Mapping(target = "password", source = "request.password")
    Account toAccount(AccountCreationRequest request);

    AccountResponse toAccountResponse(Account account);

    @Mapping(target = "roles", ignore = true)
    void updateAccount(@MappingTarget Account account, AccountUpdateRequest request);
}
