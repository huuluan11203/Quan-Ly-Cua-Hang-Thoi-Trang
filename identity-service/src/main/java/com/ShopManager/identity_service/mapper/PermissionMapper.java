package com.ShopManager.identity_service.mapper;

import org.mapstruct.Mapper;

import com.ShopManager.identity_service.DTO.request.PermissionRequest;
import com.ShopManager.identity_service.DTO.response.PermissionResponse;
import com.ShopManager.identity_service.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}