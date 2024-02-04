package com.brainstation23.ecommerce.ecommerce.service.impl;

import com.brainstation23.ecommerce.ecommerce.exception.custom.NotFoundException;
import com.brainstation23.ecommerce.ecommerce.mapper.OrderMapper;
import com.brainstation23.ecommerce.ecommerce.model.domain.Order;
import com.brainstation23.ecommerce.ecommerce.model.dto.order.OrderCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.order.OrderUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.model.enums.OrderStatus;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.OrderEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.repository.OrderRepository;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private static final String ORDER_NOT_FOUND = "Order Not Found";
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public Page<Order> getAll(Pageable pageable) {
        var entities = orderRepository.findAll(pageable);
        return entities.map(orderMapper::entityToDomain);
    }

    @Override
    public Order getOne(UUID id) {
        var entity = orderRepository.findById(id).orElseThrow(()->new NotFoundException(ORDER_NOT_FOUND));
        return orderMapper.entityToDomain(entity);
    }

    @Override
    public UUID createOne(OrderCreateRequest createRequest) {
        var entity = new OrderEntity();
        entity.setOrderDate(Timestamp.from(Instant.now()))
                .setUser(createRequest.getUser())
                .setTotalAmount(createRequest.getTotalAmount())
                .setDeliveryAddress(createRequest.getDeliveryAddress())
                .setStatus(OrderStatus.PENDING);
        var createdEntity = orderRepository.save(entity);
        return createdEntity.getId();
    }

    @Override
    public void updateOne(UUID id, OrderUpdateRequest updateRequest) {
        var entity = orderRepository.findById(id).orElseThrow(()->new NotFoundException(ORDER_NOT_FOUND));
        entity
                .setUser(updateRequest.getUser())
                .setTotalAmount(updateRequest.getTotalAmount())
                .setDeliveryAddress(updateRequest.getDeliveryAddress())
                .setStatus(updateRequest.getStatus());
        orderRepository.save(entity);
    }

    @Override
    public void deleteOne(UUID id) {
        orderRepository.deleteById(id);
    }
}
