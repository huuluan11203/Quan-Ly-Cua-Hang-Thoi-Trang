package com.ShopManager.identity_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Permission {
    @Id
    @Column(unique = true)
    String name;

    String description;
}
