package com.ShopManager.user_service.entity;


import com.ShopManager.user_service.validator.DobConstraint;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User {

    @Id
    String id;

    String firstName;
    String lastName;

    @Enumerated(EnumType.STRING)
    Gender gender;

    LocalDate dob;

    @Column(unique = true, columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    String CIC;

    @Column(unique = true)
    String phoneNumber;

    LocalDate startDate;

    @Enumerated(EnumType.STRING)
    Status status;

    @ManyToOne
    @JoinColumn(name = "position")
    Position position;


}
