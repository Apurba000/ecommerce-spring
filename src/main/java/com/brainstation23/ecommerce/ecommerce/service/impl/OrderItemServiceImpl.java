package com.brainstation23.ecommerce.ecommerce.service.impl;

import com.brainstation23.ecommerce.ecommerce.exception.custom.NotFoundException;
import com.brainstation23.ecommerce.ecommerce.mapper.OrderItemMapper;
import com.brainstation23.ecommerce.ecommerce.model.domain.OrderItem;
import com.brainstation23.ecommerce.ecommerce.model.dto.orderItem.OrderItemCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.orderItem.OrderItemUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.CartItemEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.OrderItemEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.repository.OrderItemRepository;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.OrderItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderItemServiceImpl{
    private static final String ORDER_ITEM_NOT_FOUND = "Order Item Not Found";
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;
//
//    @Override
//    public Page<OrderItem> getAll(Pageable pageable) {
//        var entities = orderItemRepository.findAll(pageable);
//        return entities.map(orderItemMapper::entityToDomain);
//    }
//
//    @Override
//    public OrderItem getOne(Long id) {
//        var entity = orderItemRepository.findById(id).orElseThrow(()->new NotFoundException(ORDER_ITEM_NOT_FOUND));
//        return orderItemMapper.entityToDomain(entity);
//    }
//
//    @Override
//    public Long createOne(OrderItemCreateRequest createRequest) {
//        var entity = new OrderItemEntity();
//        //OtherCode
//        var createdEntity = orderItemRepository.save(entity);
//        return createdEntity.getId();
//    }
//
//    @Override
//    public void updateOne(Long id, OrderItemUpdateRequest updateRequest) {
//        var entity = orderItemRepository.findById(id).orElseThrow(()->new NotFoundException(ORDER_ITEM_NOT_FOUND));
//        //Do Some Code
//        orderItemRepository.save(entity);
//    }
//
//    @Override
//    public void deleteOne(Long id) {
//        orderItemRepository.deleteById(id);
//    }
}
