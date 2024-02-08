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

import java.util.*;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {

    @NotEmpty
    private UUID id;
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
    private List<AddressEntity> address = new ArrayList<>();
}
