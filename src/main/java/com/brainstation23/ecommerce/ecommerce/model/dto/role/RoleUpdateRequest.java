package com.brainstation23.ecommerce.ecommerce.model.dto.role;

import com.brainstation23.ecommerce.ecommerce.model.enums.ERole;
import jakarta.validation.constraints.NotEmpty;
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
public class RoleUpdateRequest {
    @NotEmpty
    private ERole name;
}
