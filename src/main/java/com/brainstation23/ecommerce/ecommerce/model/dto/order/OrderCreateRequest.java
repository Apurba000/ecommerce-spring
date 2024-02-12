package com.brainstation23.ecommerce.ecommerce.model.dto.order;

import com.brainstation23.ecommerce.ecommerce.model.domain.Address;
import com.brainstation23.ecommerce.ecommerce.model.domain.OrderItem;
import com.brainstation23.ecommerce.ecommerce.model.domain.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateRequest {
    @NotNull
    private User user;
    @NotEmpty
    private BigDecimal totalAmount;
    @NotEmpty
    private Address deliveryAddress;
    @NotNull
    private Set<OrderItem> items;
}
