package com.ShopManager.identity_service.DTO.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
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
