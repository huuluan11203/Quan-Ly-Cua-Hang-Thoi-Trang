package com.ShopManager.user_service.mapper;

import com.ShopManager.user_service.DTO.request.UserCreationRequest;
import com.ShopManager.user_service.DTO.request.UserUpdateRequest;
import com.ShopManager.user_service.DTO.response.UserResponse;
import com.ShopManager.user_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
