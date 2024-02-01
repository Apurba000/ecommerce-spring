package com.brainstation23.ecommerce.ecommerce.mapper;

import com.brainstation23.ecommerce.ecommerce.model.domain.Order;
import com.brainstation23.ecommerce.ecommerce.model.dto.OrderResponse;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order entityToDomain(OrderEntity entity);
    OrderResponse domainToResponse(Order order);
}
