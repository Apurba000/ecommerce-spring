package com.brainstation23.ecommerce.ecommerce.service.impl;

import com.brainstation23.ecommerce.ecommerce.exception.custom.NotFoundException;
import com.brainstation23.ecommerce.ecommerce.mapper.ProductMapper;
import com.brainstation23.ecommerce.ecommerce.model.domain.Product;
import com.brainstation23.ecommerce.ecommerce.model.dto.product.ProductCreateUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.CategoryEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.ProductEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.repository.CategoryRepository;
import com.brainstation23.ecommerce.ecommerce.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.JDBCException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private static final String PRODUCT_NOT_FOUND = "Product Not Found";
    private static final String CATEGORY_CANT_BE_EMPTY = "Category cant be EMPTY";
    private static final String CATEGORY_NOT_FOUND = "Category Dont Exist";
    private static final String PRODUCT_ID_NOT_EMPTY = "Product Id cant be EMPTY";

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;


    public Page<Product> getAll(Pageable pageable) {
        var entities = productRepository.findAll(pageable);
        return entities.map(productMapper::entityToDomain);
    }

    public Product getOne(UUID id) {
        var entity = productRepository.findById(id)
                .orElseThrow(()->new NotFoundException(PRODUCT_NOT_FOUND));
        return productMapper.entityToDomain(entity);
    }

    public UUID createOne(ProductCreateUpdateRequest createRequest) {
        var productEntity = getProductEntity(createRequest);
        var createdEntity = productRepository.save(productEntity);
        return createdEntity.getId();
    }

    private ProductEntity getProductEntity(ProductCreateUpdateRequest request){
        var cateGoryId = request.getCateGoryId();
        Set<CategoryEntity> categories = new HashSet<>();

        var productEntity = productMapper.requestToEntity(request);
        if (StringUtils.isEmpty(cateGoryId)){
            throw new NotFoundException(CATEGORY_CANT_BE_EMPTY);
        } else {
            CategoryEntity category = categoryRepository.findById(UUID.fromString(cateGoryId))
                    .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND));
            categories.add(category);
        }
        return productEntity.setCategories(categories);
    }

    public void updateOne(ProductCreateUpdateRequest updateRequest) {
        if (updateRequest.getId() == null) throw new NotFoundException(PRODUCT_ID_NOT_EMPTY);
        var productEntity = getProductEntity(updateRequest);
        productRepository.save(productEntity);
    }

    public void deleteOne(UUID id) {
        productRepository.deleteById(id);
    }
}
