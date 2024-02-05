package com.brainstation23.ecommerce.ecommerce.model.dto.category;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CategoryUpdateRequest {
    @NotEmpty
    private UUID id;
    @NotEmpty
    private String categoryName;
}
