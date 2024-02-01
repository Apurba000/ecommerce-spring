package com.brainstation23.ecommerce.ecommerce.persistence.repository;

import com.brainstation23.ecommerce.ecommerce.persistence.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity,Long> {
}
