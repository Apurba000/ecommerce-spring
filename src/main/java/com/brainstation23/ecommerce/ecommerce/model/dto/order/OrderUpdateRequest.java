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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderUpdateRequest {
    @NotNull
    private UserEntity user;
    @NotEmpty
    private BigDecimal totalAmount;
    @NotEmpty
    private OrderStatus status;
    @NotEmpty
    private AddressEntity deliveryAddress;
}
