package com.brainstation23.ecommerce.ecommerce.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor

public class OrderItem {
    private UUID id;

    private int quantity;

    private BigDecimal unitPrice;

    private Order order;

    private Product product;
}
