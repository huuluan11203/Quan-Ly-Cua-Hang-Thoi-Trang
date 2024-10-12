package com.ShopManager.identity_service.controller;


import com.ShopManager.identity_service.DTO.request.AccountCreationRequest;
import com.ShopManager.identity_service.DTO.request.AccountUpdateRequest;
import com.ShopManager.identity_service.DTO.response.AccountResponse;
import com.ShopManager.identity_service.DTO.response.ApiResponse;
import com.ShopManager.identity_service.service.AccountService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccountController {
    AccountService accountService;

    @PostMapping
    ApiResponse<AccountResponse> createAccount(@RequestBody @Valid AccountCreationRequest request) {
        return ApiResponse.<AccountResponse>builder()
                .result(accountService.createAccount(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<AccountResponse>> getAccounts() {
        return ApiResponse.<List<AccountResponse>>builder()
                .result(accountService.getAccounts())
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<AccountResponse> getAccount(@PathVariable("userId") String userId) {
        return ApiResponse.<AccountResponse>builder()
                .result(accountService.getAccount(userId))
                .build();
    }

    @GetMapping("/my-info")
    ApiResponse<AccountResponse> getMyInfo() {
        return ApiResponse.<AccountResponse>builder()
                .result(accountService.getMyInfo())
                .build();
    }

    @DeleteMapping("/{userId}")
    ApiResponse<String> deleteAccount(@PathVariable String userId) {
        accountService.deleteAccount(userId);
        return ApiResponse.<String>builder().result("User has been deleted").build();
    }

    @PutMapping("/{userId}")
    ApiResponse<AccountResponse> updateAccount(@PathVariable String userId, @RequestBody AccountUpdateRequest request) {
        return ApiResponse.<AccountResponse>builder()
                .result(accountService.updateAccount(userId, request))
                .build();
    }
}
