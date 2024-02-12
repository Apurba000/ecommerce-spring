package com.brainstation23.ecommerce.ecommerce.model.domain;

import com.brainstation23.ecommerce.ecommerce.model.enums.OrderStatus;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.OrderItemEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Accessors(chain = true)
public class Order {
    private UUID id;

    private UserEntity user;

    private BigDecimal totalAmount;

    private Timestamp orderDate;

    private OrderStatus status;
    private Set<OrderItemEntity> items;
}
