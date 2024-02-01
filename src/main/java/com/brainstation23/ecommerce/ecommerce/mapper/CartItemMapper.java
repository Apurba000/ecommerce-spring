package com.brainstation23.ecommerce.ecommerce.mapper;

import com.brainstation23.ecommerce.ecommerce.model.domain.CartItem;
import com.brainstation23.ecommerce.ecommerce.model.dto.cartItem.CartItemResponse;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.CartItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItem entityToDomain(CartItemEntity entity);
    CartItemResponse domainToResponse(CartItem cartItem);
}
