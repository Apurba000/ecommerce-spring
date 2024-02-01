package com.brainstation23.ecommerce.ecommerce.service.interfaces;

import com.brainstation23.ecommerce.ecommerce.model.domain.CartItem;
import com.brainstation23.ecommerce.ecommerce.model.dto.cartItem.CartItemCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.cartItem.CartItemUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CartItemService {
    Page<CartItem> getAll(Pageable pageable);
    CartItem getOne(Long id);
    Long createOne(CartItemCreateRequest createRequest);
    void updateOne(Long id, CartItemUpdateRequest updateRequest);
    void deleteOne(Long id);
}
