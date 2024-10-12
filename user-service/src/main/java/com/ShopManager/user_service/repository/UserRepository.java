package com.ShopManager.user_service.repository;

import com.ShopManager.user_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, String> {
    boolean existsByCIC(String CIC);

}
