package com.brainstation23.ecommerce.ecommerce.service.impl;

import com.brainstation23.ecommerce.ecommerce.exception.custom.NotFoundException;
import com.brainstation23.ecommerce.ecommerce.mapper.CartItemMapper;
import com.brainstation23.ecommerce.ecommerce.model.domain.CartItem;
import com.brainstation23.ecommerce.ecommerce.model.dto.cartItem.CartItemCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.cartItem.CartItemUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.CartItemEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.ProductEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.repository.CartItemRepository;
import com.brainstation23.ecommerce.ecommerce.persistence.repository.ProductRepository;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.CartItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService{
    private static final String CART_ITEM_NOT_FOUND = "Cart Item Not Found";
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CartItemMapper cartItemMapper;

    @Override
    public Page<CartItem> getAll(Pageable pageable) {
        var entities = cartItemRepository.findAll(pageable);
        return entities.map(cartItemMapper::entityToDomain);
    }

    @Override
    public CartItem getOne(UUID id) {
        var entity =  cartItemRepository.findById(id).orElseThrow(()->new NotFoundException(CART_ITEM_NOT_FOUND));
        return cartItemMapper.entityToDomain(entity);
    }

    @Override
    public UUID createOne(CartItemCreateRequest createRequest) {
        var entity = new CartItemEntity();

        entity.setProduct(getProduct(createRequest.getProductId()))
                .setDate(createRequest.getDate())
                .setQuantity(createRequest.getQuantity());
        var createdEntity = cartItemRepository.save(entity);
        return createdEntity.getId();
    }

    @Override
    public void updateOne(UUID id, CartItemUpdateRequest updateRequest) {
        var entity = cartItemRepository.findById(id).orElseThrow(()->new NotFoundException(CART_ITEM_NOT_FOUND));
        entity.setProduct(getProduct(updateRequest.getProductId()))
                .setDate(updateRequest.getDate())
                .setQuantity(updateRequest.getQuantity());
        cartItemRepository.save(entity);
    }

    @Override
    public void deleteOne(UUID id) {
        cartItemRepository.deleteById(id);
    }

    private ProductEntity getProduct(UUID id)
    {
        return productRepository.findById(id).orElseThrow(()->new NotFoundException("No Product Found"));
    }
}
