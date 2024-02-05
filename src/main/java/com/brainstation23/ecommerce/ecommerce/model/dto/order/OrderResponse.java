package com.brainstation23.ecommerce.ecommerce.model.dto.order;

import com.brainstation23.ecommerce.ecommerce.model.enums.OrderStatus;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.AddressEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private UserEntity user;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private AddressEntity deliveryAddress;
}
