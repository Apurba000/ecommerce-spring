package com.brainstation23.ecommerce.ecommerce.service.impl;

import com.brainstation23.ecommerce.ecommerce.exception.custom.NotFoundException;
import com.brainstation23.ecommerce.ecommerce.mapper.AddressMapper;
import com.brainstation23.ecommerce.ecommerce.mapper.OrderItemMapper;
import com.brainstation23.ecommerce.ecommerce.mapper.OrderMapper;
import com.brainstation23.ecommerce.ecommerce.mapper.UserMapper;
import com.brainstation23.ecommerce.ecommerce.model.domain.Order;
import com.brainstation23.ecommerce.ecommerce.model.dto.order.OrderCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.order.OrderUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.model.enums.OrderStatus;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.AddressEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.OrderEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.OrderItemEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.UserEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.repository.OrderRepository;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private static final String ORDER_NOT_FOUND = "Order Not Found";
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserMapper userMapper;
    private final OrderItemMapper orderItemMapper;
    private final AddressMapper addressMapper;

    @Override
    public Page<Order> getAll(Pageable pageable) {
        var entities = orderRepository.findAll(pageable);
        return entities.map(orderMapper::entityToDomain);
    }

    @Override
    public Order getOne(UUID id) {
        var entity = orderRepository.findById(id).orElseThrow(()->new NotFoundException(ORDER_NOT_FOUND));
        entity.getItems().size();
        return orderMapper.entityToDomain(entity);
    }

    @Override
    public UUID createOne(OrderCreateRequest createRequest) {
        UserEntity userEntity = userMapper.domainToEntity(createRequest.getUser());
        AddressEntity addressEntity = addressMapper.domainToEntity(createRequest.getDeliveryAddress());
        Set<OrderItemEntity> orderItemEntities = createRequest.getItems().stream()
                .map(orderItemMapper::domainToEntity).collect(Collectors.toSet());

        var entity = new OrderEntity();
        entity.setOrderDate(Timestamp.from(Instant.now()))
                .setUser(userEntity)
                .setTotalAmount(createRequest.getTotalAmount())
                .setDeliveryAddress(addressEntity)
                .setStatus(OrderStatus.PENDING)
                .setItems(orderItemEntities);
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

    @Override
    public void updateOrderStatus(UUID id, OrderStatus orderStatus) {
        var order = orderRepository.findById(id).orElseThrow(()->new NotFoundException(ORDER_NOT_FOUND));
        order.setStatus(orderStatus);
        orderRepository.save(order);
    }
}
