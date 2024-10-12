package com.ShopManager.identity_service.DTO.request;


import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountCreationRequest {
    @Size(min = 4, message = "USERNAME_INVALID")
    String username;

    @Size(min = 6, message = "INVALID_PASSWORD")
    String password;

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
