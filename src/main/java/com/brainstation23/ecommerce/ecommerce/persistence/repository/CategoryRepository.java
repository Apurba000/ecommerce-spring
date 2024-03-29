package com.brainstation23.ecommerce.ecommerce.persistence.repository;

import com.brainstation23.ecommerce.ecommerce.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
    Optional<CategoryEntity> findByCategoryName(String categoryName);
}
