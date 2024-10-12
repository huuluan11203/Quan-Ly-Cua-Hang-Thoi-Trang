package com.ShopManager.identity_service.mapper;

import com.ShopManager.identity_service.DTO.request.AccountCreationRequest;
import com.ShopManager.identity_service.DTO.request.UserCreationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "dob", target = "dob")
    @Mapping(source = "CIC", target = "CIC")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "position", target = "position")
    UserCreationRequest toUserCreationRequest(AccountCreationRequest request);
}
