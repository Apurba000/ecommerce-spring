package com.brainstation23.ecommerce.ecommerce.service.impl;

import com.brainstation23.ecommerce.ecommerce.exception.custom.NotFoundException;
import com.brainstation23.ecommerce.ecommerce.mapper.CategoryMapper;
import com.brainstation23.ecommerce.ecommerce.model.domain.Category;
import com.brainstation23.ecommerce.ecommerce.model.dto.category.CategoryCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.category.CategoryUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.CategoryEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.repository.CategoryRepository;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private static final String CATEGORY_NOT_FOUND = "Category Not Found";
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Page<Category> getAll(Pageable pageable) {
        var entities = categoryRepository.findAll(pageable);
        return entities.map(categoryMapper::entityToDomain);
    }

    @Override
    public List<Category> getAll() {
        List<CategoryEntity> entities =  categoryRepository.findAll();
        return entities.stream()
                .map(categoryMapper::entityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Category getOne(UUID id) {
        var entity =  categoryRepository.findById(id).orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        return categoryMapper.entityToDomain(entity);
    }

    @Override
    public UUID createOne(CategoryCreateRequest createRequest) {
        var entity = new CategoryEntity();
        entity.setCategoryName(createRequest.getCategoryName());
        var createdEntity = categoryRepository.save(entity);
        return createdEntity.getId();
    }

    @Override
    public void updateOne(UUID id, CategoryUpdateRequest updateRequest) {
        var entity = categoryRepository.findById(id).orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        entity.setCategoryName(updateRequest.getCategoryName());
        categoryRepository.save(entity);
    }

    @Override
    public void deleteOne(UUID id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category getByName(String name) {
        var entity = categoryRepository.findByCategoryName(name).orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        return categoryMapper.entityToDomain(entity);
    }
}
