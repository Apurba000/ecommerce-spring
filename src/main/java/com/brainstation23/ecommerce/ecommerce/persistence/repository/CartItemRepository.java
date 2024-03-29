package com.brainstation23.ecommerce.ecommerce.persistence.repository;

import com.brainstation23.ecommerce.ecommerce.persistence.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, UUID> {
//    Optional<CartItemEntity> findByProductId(UUID productId);
    Optional<CartItemEntity> findByProductIdAndUserId(UUID productId, UUID userId);
}
