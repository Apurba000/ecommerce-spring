package com.brainstation23.ecommerce.ecommerce.service.impl;

import com.brainstation23.ecommerce.ecommerce.exception.custom.NotFoundException;
import com.brainstation23.ecommerce.ecommerce.mapper.CartItemMapper;
import com.brainstation23.ecommerce.ecommerce.model.domain.CartItem;
import com.brainstation23.ecommerce.ecommerce.model.dto.cartItem.CartItemCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.cartItem.CartItemUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.AddressEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.CartItemEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.repository.CartItemRepository;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.CartItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartItemServiceImpl{
    private static final String CART_ITEM_NOT_FOUND = "Cart Item Not Found";
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;
//
//    @Override
//    public Page<CartItem> getAll(Pageable pageable) {
//        var entities = cartItemRepository.findAll(pageable);
//        return entities.map(cartItemMapper::entityToDomain);
//    }
//
//    @Override
//    public CartItem getOne(Long id) {
//        var entity =  cartItemRepository.findById(id).orElseThrow(()->new NotFoundException(CART_ITEM_NOT_FOUND));
//        return cartItemMapper.entityToDomain(entity);
//    }
//
//    @Override
//    public Long createOne(CartItemCreateRequest createRequest) {
//        var entity = new CartItemEntity();
//        //OtherCode
//        var createdEntity = cartItemRepository.save(entity);
//        return createdEntity.getId();
//    }
//
//    @Override
//    public void updateOne(Long id, CartItemUpdateRequest updateRequest) {
//        var entity = cartItemRepository.findById(id).orElseThrow(()->new NotFoundException(CART_ITEM_NOT_FOUND));
//        //Do Some Code
//        cartItemRepository.save(entity);
//    }
//
//    @Override
//    public void deleteOne(Long id) {
//        cartItemRepository.deleteById(id);
//    }
}
