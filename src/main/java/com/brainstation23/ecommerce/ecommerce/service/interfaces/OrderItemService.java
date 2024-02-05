package com.brainstation23.ecommerce.ecommerce.service.interfaces;

import com.brainstation23.ecommerce.ecommerce.model.domain.OrderItem;
import com.brainstation23.ecommerce.ecommerce.model.dto.orderItem.OrderItemCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.orderItem.OrderItemUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface OrderItemService {
    Page<OrderItem> getAll(Pageable pageable);
    OrderItem getOne(UUID id);
    UUID createOne(OrderItemCreateRequest createRequest);
    void updateOne(UUID id, OrderItemUpdateRequest updateRequest);
    void deleteOne(UUID id);
}
