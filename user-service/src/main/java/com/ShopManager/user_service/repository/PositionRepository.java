package com.ShopManager.user_service.repository;

import com.ShopManager.user_service.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, String> {
    Optional<Position> findById(String id);
    Optional<Position> findByName(String name);
}
