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
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl {
    private static final String CATEGORY_NOT_FOUND = "Category Not Found";
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
//
//    @Override
//    public Page<Category> getAll(Pageable pageable) {
//
//        var entities = categoryRepository.findAll(pageable);
//        return entities.map(categoryMapper::entityToDomain);
//    }
//
//    @Override
//    public Category getOne(Long id) {
//        var entity =  categoryRepository.findById(id).orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
//        return categoryMapper.entityToDomain(entity);
//    }
//
//    @Override
//    public Long createOne(CategoryCreateRequest createRequest) {
//        var entity = new CategoryEntity();
//        //OtherCode
//        var createdEntity = categoryRepository.save(entity);
//        return createdEntity.getId();
//    }
//
//    @Override
//    public void updateOne(Long id, CategoryUpdateRequest updateRequest) {
//        var entity = categoryRepository.findById(id).orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
//        //Do Some Code
//        categoryRepository.save(entity);
//    }
//
//    @Override
//    public void deleteOne(Long id) {
//        categoryRepository.deleteById(id);
//    }
}
