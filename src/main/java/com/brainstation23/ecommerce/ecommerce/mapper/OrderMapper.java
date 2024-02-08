package com.brainstation23.ecommerce.ecommerce.mapper;

import com.brainstation23.ecommerce.ecommerce.model.domain.Order;
import com.brainstation23.ecommerce.ecommerce.model.dto.order.OrderAdminResponse;
import com.brainstation23.ecommerce.ecommerce.model.dto.order.OrderResponse;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order entityToDomain(OrderEntity entity);
    OrderResponse domainToResponse(Order order);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "user.id", target = "user_id")
    @Mapping(source = "totalAmount", target = "totalAmount")
    @Mapping(source = "orderDate", target = "orderDate")
    @Mapping(source = "status", target = "status")
    OrderAdminResponse domainToOrderAdminResponse(Order order);
}
