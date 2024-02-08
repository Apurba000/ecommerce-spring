package com.brainstation23.ecommerce.ecommerce.model.dto.cartItem;

import com.brainstation23.ecommerce.ecommerce.persistence.entity.UserEntity;
import jakarta.validation.constraints.NotEmpty;
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
public class CartItemCreateUpdateRequest {
    private UUID id;
    private UUID productId;
    @NotEmpty
    private int quantity;
    private Timestamp date;

    private UserEntity user;
}
