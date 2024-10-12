package com.ShopManager.identity_service.DTO.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountUpdateRequest {
    String password;

    List<String> roles;
}
