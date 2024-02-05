package com.brainstation23.ecommerce.ecommerce.service.interfaces;

import com.brainstation23.ecommerce.ecommerce.model.domain.Order;
import com.brainstation23.ecommerce.ecommerce.model.dto.order.OrderCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.order.OrderUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface OrderService {
    Page<Order> getAll(Pageable pageable);
    Order getOne(UUID id);
    UUID createOne(OrderCreateRequest createRequest);
    void updateOne(UUID id, OrderUpdateRequest updateRequest);
    void deleteOne(UUID id);
}
