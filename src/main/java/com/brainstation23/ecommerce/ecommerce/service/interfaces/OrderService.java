package com.brainstation23.ecommerce.ecommerce.service.interfaces;

import com.brainstation23.ecommerce.ecommerce.model.domain.Order;
import com.brainstation23.ecommerce.ecommerce.model.dto.order.OrderCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.order.OrderUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    Page<Order> getAll(Pageable pageable);
    Order getOne(Long id);
    Long createOne(OrderCreateRequest createRequest);
    void updateOne(Long id, OrderUpdateRequest updateRequest);
    void deleteOne(Long id);
}
