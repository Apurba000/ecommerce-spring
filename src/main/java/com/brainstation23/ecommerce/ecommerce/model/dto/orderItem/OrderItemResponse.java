package com.brainstation23.ecommerce.ecommerce.model.dto.orderItem;

import com.brainstation23.ecommerce.ecommerce.persistence.entity.OrderEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.ProductEntity;
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
public class OrderItemResponse {
    private int quantity;
    private BigDecimal unitPrice;
    private OrderEntity order;
    private ProductEntity product;
}
