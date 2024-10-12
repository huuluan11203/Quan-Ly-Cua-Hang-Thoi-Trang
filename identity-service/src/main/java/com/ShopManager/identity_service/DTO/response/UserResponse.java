package com.ShopManager.identity_service.DTO.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String firstName;
    String lastName;
    String gender;
    LocalDate dob;
    String CIC;
    String phoneNumber;
    LocalDate startDate;
    String status;
    String position;
}
