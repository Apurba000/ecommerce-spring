package com.brainstation23.ecommerce.ecommerce.model.dto.orderItem;

import com.brainstation23.ecommerce.ecommerce.persistence.entity.OrderEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.ProductEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class OrderItemCreateRequest {
    @NotEmpty
    private int quantity;
    @NotEmpty
    private BigDecimal unitPrice;
    @NotNull
    private OrderEntity order;
    @NotNull
    private ProductEntity product;
}
