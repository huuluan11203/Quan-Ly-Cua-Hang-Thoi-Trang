package com.ShopManager.identity_service.client;

import com.ShopManager.identity_service.DTO.request.UserCreationRequest;
import com.ShopManager.identity_service.DTO.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", url = "${app.services.user}")
public interface UserClient {
    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    UserResponse createUser(@RequestBody UserCreationRequest request);
}
