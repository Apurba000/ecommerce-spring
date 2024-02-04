package com.brainstation23.ecommerce.ecommerce.model.dto.user;

import com.brainstation23.ecommerce.ecommerce.persistence.entity.AddressEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.CartItemEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.RoleEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    @NotEmpty
    @Size(max = 50)
    private String firstname;
    @NotEmpty
    @Size(max = 50)
    private String lastname;
    @NotEmpty
    @Size(max = 50)
    private String username;
    @NotEmpty
    @Size(max = 255)
    private String email;
    @NotEmpty
    @Size(max = 20)
    private String phone;
    @NotEmpty
    @Size(max = 255)
    private String password;
    @NotEmpty
    private Set<RoleEntity> roles = new HashSet<>();
    @NotEmpty
    private List<AddressEntity> address = new ArrayList<>();
    @NotEmpty
    private List<CartItemEntity> cartItems = new ArrayList<>();
}
