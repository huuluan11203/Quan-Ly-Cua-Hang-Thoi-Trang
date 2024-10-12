package com.ShopManager.user_service.DTO.response;

import java.time.LocalDate;
import java.util.Set;

import com.ShopManager.user_service.entity.Gender;
import com.ShopManager.user_service.entity.Position;
import com.ShopManager.user_service.entity.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String firstName;
    String lastName;
    Gender gender;
    LocalDate dob;
    String CIC;
    String phoneNumber;
    LocalDate startDate;
    Status status;
    String positionName;

}
