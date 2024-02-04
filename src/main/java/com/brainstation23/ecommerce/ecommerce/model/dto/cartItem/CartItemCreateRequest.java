package com.brainstation23.ecommerce.ecommerce.model.dto.cartItem;

import com.brainstation23.ecommerce.ecommerce.persistence.entity.ProductEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CartItemCreateRequest {
    private ProductEntity product;
    private int quantity;
    private Timestamp date;
}
