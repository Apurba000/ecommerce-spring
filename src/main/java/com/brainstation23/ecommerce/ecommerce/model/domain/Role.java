package com.brainstation23.ecommerce.ecommerce.model.domain;

import com.brainstation23.ecommerce.ecommerce.model.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Role {
    private UUID id;
    private ERole name;
}
