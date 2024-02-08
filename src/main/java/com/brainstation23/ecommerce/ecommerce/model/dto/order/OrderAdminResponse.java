package com.brainstation23.ecommerce.ecommerce.model.dto.order;

import com.brainstation23.ecommerce.ecommerce.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@Accessors(chain = true)
public class OrderAdminResponse {
    private UUID id;

    private UUID user_id;

    private BigDecimal totalAmount;

    private Timestamp orderDate;

    private OrderStatus status;
}
