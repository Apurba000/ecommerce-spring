package com.brainstation23.ecommerce.ecommerce.model.dto.order;

import com.brainstation23.ecommerce.ecommerce.model.enums.OrderStatus;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.AddressEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.UserEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderUpdateRequest {
    private UUID id;
    private UserEntity user;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private AddressEntity deliveryAddress;
}
