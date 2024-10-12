package com.ShopManager.user_service.DTO.request;


import com.ShopManager.user_service.entity.Gender;
import com.ShopManager.user_service.entity.Position;
import com.ShopManager.user_service.entity.Status;
import com.ShopManager.user_service.validator.DobConstraint;
import com.ShopManager.user_service.validator.PhoneNumberConstraint;
import com.ShopManager.user_service.validator.StartDateConstraint;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {

    String firstName;
    String lastName;
    String gender;

    @DobConstraint(min = 18)
    LocalDate dob;

    String CIC;

    @PhoneNumberConstraint()
    String phoneNumber;

    @StartDateConstraint()
    LocalDate startDate;

    String status;
    String position;
}
