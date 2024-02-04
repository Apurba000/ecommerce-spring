package com.brainstation23.ecommerce.ecommerce.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String phone;
}
