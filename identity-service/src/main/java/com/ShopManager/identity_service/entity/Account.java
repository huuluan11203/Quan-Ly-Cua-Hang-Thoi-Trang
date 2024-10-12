package com.ShopManager.identity_service.entity;


import java.util.Set;

import jakarta.persistence.*;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "username", unique = true, columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    String username;

    @Size(min = 8)
    @Column(nullable = false)
    String password;

    @ManyToMany
    Set<Role> roles;

}

