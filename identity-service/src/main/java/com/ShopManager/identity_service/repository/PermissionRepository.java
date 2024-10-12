package com.ShopManager.identity_service.repository;


import com.ShopManager.identity_service.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {
    boolean existsByName(String s);
    List<Permission> findByNameStartingWith(String prefix);
}
