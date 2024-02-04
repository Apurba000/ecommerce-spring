package com.brainstation23.ecommerce.ecommerce.model.dto.cartItem;

import com.brainstation23.ecommerce.ecommerce.persistence.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CartItemUpdateRequest {
    private ProductEntity product;
    private int quantity;
    private Timestamp date;
}
