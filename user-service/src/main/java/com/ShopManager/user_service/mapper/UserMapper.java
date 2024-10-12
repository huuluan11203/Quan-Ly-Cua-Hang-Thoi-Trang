package com.ShopManager.user_service.mapper;

import com.ShopManager.user_service.DTO.request.UserCreationRequest;
import com.ShopManager.user_service.DTO.request.UserUpdateRequest;
import com.ShopManager.user_service.DTO.response.UserResponse;
import com.ShopManager.user_service.entity.Gender;
import com.ShopManager.user_service.entity.Position;
import com.ShopManager.user_service.entity.Status;
import com.ShopManager.user_service.entity.User;


import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "status",ignore = true)
    @Mapping(target = "gender",ignore = true)
    @Mapping(target = "position",ignore = true)
    User toUser(UserCreationRequest request);


    @Mapping(target = "positionName", expression = "java(user.getPosition() != null ? user.getPosition().getName() : null)")
    UserResponse toUserResponse(User user);


    @Mapping(target = "status",ignore = true)
    @Mapping(target = "gender",ignore = true)
    @Mapping(target = "position",ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);


    @Named("mapGender")
    default Gender mapGender(String gender) {
        return Gender.valueOf(gender.toUpperCase());
    }
    @Named("mapStatus")
    default Status mapStatus(String status) {
        return Status.valueOf(status.toUpperCase());
    }

}
