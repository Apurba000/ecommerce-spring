package com.brainstation23.ecommerce.ecommerce.model.dto.cartItem;

import com.brainstation23.ecommerce.ecommerce.model.dto.product.ProductResponse;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.ProductEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.UserEntity;
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
public class CartItemResponse {
    private UUID id;
    private ProductResponse product;
    private int quantity;
    private Timestamp date;
}

