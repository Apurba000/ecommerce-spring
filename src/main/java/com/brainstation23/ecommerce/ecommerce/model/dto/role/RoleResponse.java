package com.brainstation23.ecommerce.ecommerce.model.dto.role;

import com.brainstation23.ecommerce.ecommerce.model.enums.ERole;
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
public class RoleResponse {
    private ERole name;
}
