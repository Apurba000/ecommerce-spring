package com.brainstation23.ecommerce.ecommerce.model.dto.cartItem;

import com.brainstation23.ecommerce.ecommerce.persistence.entity.ProductEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CartItemUpdateRequest {
    private UUID productId;
    @NotEmpty
    private int quantity;
    private Timestamp date;
}
