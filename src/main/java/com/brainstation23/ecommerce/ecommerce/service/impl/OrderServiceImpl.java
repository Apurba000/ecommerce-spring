package com.brainstation23.ecommerce.ecommerce.service.impl;

import com.brainstation23.ecommerce.ecommerce.exception.custom.NotFoundException;
import com.brainstation23.ecommerce.ecommerce.mapper.OrderMapper;
import com.brainstation23.ecommerce.ecommerce.model.domain.Order;
import com.brainstation23.ecommerce.ecommerce.model.dto.order.OrderCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.order.OrderUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.OrderEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.repository.OrderRepository;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl{
    private static final String ORDER_NOT_FOUND = "Order Not Found";
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
//
//    @Override
//    public Page<Order> getAll(Pageable pageable) {
//        var entities = orderRepository.findAll(pageable);
//        return entities.map(orderMapper::entityToDomain);
//    }
//
//    @Override
//    public Order getOne(Long id) {
//        var entity = orderRepository.findById(id).orElseThrow(()->new NotFoundException(ORDER_NOT_FOUND));
//        return orderMapper.entityToDomain(entity);
//    }
//
//    @Override
//    public Long createOne(OrderCreateRequest createRequest) {
//        var entity = new OrderEntity();
//
//        var createdEntity = orderRepository.save(entity);
//        return createdEntity.getId();
//    }
//
//    @Override
//    public void updateOne(Long id, OrderUpdateRequest updateRequest) {
//        var entity = orderRepository.findById(id).orElseThrow(()->new NotFoundException(ORDER_NOT_FOUND));
//
//        orderRepository.save(entity);
//    }
//
//    @Override
//    public void deleteOne(Long id) {
//        orderRepository.deleteById(id);
//    }
}
