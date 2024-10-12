package com.ShopManager.user_service.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Position {

    @Id
    @Column(unique = true)
    String name;

    String description;

    @Column(precision = 10, scale = 2)
    BigDecimal salary;

    @OneToMany(mappedBy = "position")
    List<User> users;


}
