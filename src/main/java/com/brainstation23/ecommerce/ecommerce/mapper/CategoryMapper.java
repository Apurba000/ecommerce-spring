package com.brainstation23.ecommerce.ecommerce.mapper;

import com.brainstation23.ecommerce.ecommerce.model.domain.Category;
import com.brainstation23.ecommerce.ecommerce.model.dto.category.CategoryResponse;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category entityToDomain(CategoryEntity entity);
    CategoryResponse domainToResponse(Category category);
}
