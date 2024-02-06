package com.brainstation23.ecommerce.ecommerce.service.interfaces;

import com.brainstation23.ecommerce.ecommerce.model.domain.Category;
import com.brainstation23.ecommerce.ecommerce.model.dto.category.CategoryCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.category.CategoryUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    Page<Category> getAll(Pageable pageable);
    List<Category> getAll();
    Category getOne(UUID id);
    UUID createOne(CategoryCreateRequest createRequest);
    void updateOne(UUID id, CategoryUpdateRequest updateRequest);
    void deleteOne(UUID id);
    Category getByName(String name);
}
