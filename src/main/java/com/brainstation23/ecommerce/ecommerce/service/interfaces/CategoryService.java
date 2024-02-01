package com.brainstation23.ecommerce.ecommerce.service.interfaces;

import com.brainstation23.ecommerce.ecommerce.model.domain.Category;
import com.brainstation23.ecommerce.ecommerce.model.dto.category.CategoryCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.category.CategoryUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Page<Category> getAll(Pageable pageable);
    Category getOne(Long id);
    Long createOne(CategoryCreateRequest createRequest);
    void updateOne(Long id, CategoryUpdateRequest updateRequest);
    void deleteOne(Long id);
}
