package com.brainstation23.ecommerce.ecommerce.model.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@ToString
@Getter
@Setter
@RequiredArgsConstructor
public class JwtSignInResponse {
    private final String token;
    private String type = "Bearer";
    private final UUID id;
    private final String username;
    private final String email;
    private final List<String> roles;

}
