package com.brainstation23.ecommerce.ecommerce.model.domain;

import com.brainstation23.ecommerce.ecommerce.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private UUID id;
    private String details;
    private String zipCode;
//    private Set<User> users;
}
