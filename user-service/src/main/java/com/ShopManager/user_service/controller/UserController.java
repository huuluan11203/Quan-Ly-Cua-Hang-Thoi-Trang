package com.ShopManager.user_service.controller;

import com.ShopManager.user_service.DTO.request.UserCreationRequest;
import com.ShopManager.user_service.DTO.request.UserUpdateRequest;
import com.ShopManager.user_service.DTO.response.ApiResponse;
import com.ShopManager.user_service.DTO.response.UserResponse;
import com.ShopManager.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping("/users")
    ApiResponse<UserResponse> createAccount(@RequestBody @Valid UserCreationRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }
    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateAccount(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(userId, request))
                .build();
    }

    @DeleteMapping("/{userId}")
    ApiResponse<String> deleteAccount(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ApiResponse.<String>builder().result("User has been deleted").build();
    }

}
