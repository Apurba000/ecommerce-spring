package com.brainstation23.ecommerce.ecommerce.model.dto.Address;

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
public class AddressCreateRequest {
    @NotEmpty
    private String details;

    @NotEmpty
    private String zipCode;
}
