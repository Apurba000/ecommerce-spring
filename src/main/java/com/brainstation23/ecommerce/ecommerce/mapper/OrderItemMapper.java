package com.brainstation23.ecommerce.ecommerce.mapper;

import com.brainstation23.ecommerce.ecommerce.model.domain.Order;
import com.brainstation23.ecommerce.ecommerce.model.domain.OrderItem;
import com.brainstation23.ecommerce.ecommerce.model.dto.order.OrderResponse;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.OrderItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItem entityToDomain(OrderItemEntity entity);
    OrderResponse domainToResponse(OrderItem orderItem);
    OrderItemEntity domainToEntity(OrderItem orderItem);
}
