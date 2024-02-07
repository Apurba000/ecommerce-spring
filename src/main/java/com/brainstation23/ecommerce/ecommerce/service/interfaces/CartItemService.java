package com.brainstation23.ecommerce.ecommerce.service.interfaces;

import com.brainstation23.ecommerce.ecommerce.model.domain.CartItem;
import com.brainstation23.ecommerce.ecommerce.model.dto.cartItem.CartItemCreateUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CartItemService {
    Page<CartItem> getAll(Pageable pageable);
    CartItem getOne(UUID id);
    UUID createOne(CartItemCreateUpdateRequest createRequest);
    void updateOne(CartItemCreateUpdateRequest updateRequest);
    void deleteOne(UUID id);
}
