package com.ShopManager.identity_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ShopManager.identity_service.DTO.request.RoleRequest;
import com.ShopManager.identity_service.DTO.response.RoleResponse;
import com.ShopManager.identity_service.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}