package com.brainstation23.ecommerce.ecommerce.service.impl;

import com.brainstation23.ecommerce.ecommerce.exception.custom.NotFoundException;
import com.brainstation23.ecommerce.ecommerce.mapper.CategoryMapper;
import com.brainstation23.ecommerce.ecommerce.mapper.ProductMapper;
import com.brainstation23.ecommerce.ecommerce.model.domain.Product;
import com.brainstation23.ecommerce.ecommerce.model.dto.product.ProductCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.product.ProductUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.persistence.repository.CategoryRepository;
import com.brainstation23.ecommerce.ecommerce.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private static final String PRODUCT_NOT_FOUND = "Product Not Found";
    private static final String CATEGORY_CANT_BE_EMPTY = "Category cant be EMPTY";
    private static final String CATEGORY_NOT_FOUND = "Category Dont Exist";
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;


    public Page<Product> getAll(Pageable pageable) {
        var entities = productRepository.findAll(pageable);
        return entities.map(productMapper::entityToDomain);
    }


    public Product getOne(UUID id) {
        var entity = productRepository.findById(id).orElseThrow(()->new NotFoundException(PRODUCT_NOT_FOUND));
        return productMapper.entityToDomain(entity);
    }


    public UUID createOne(ProductCreateRequest createRequest) {
        var productEntity = productMapper.requestToEntity(createRequest);
        var createdEntity = productRepository.save(productEntity);
        return createdEntity.getId();
    }

    /*private Product getProductDomain(ProductCreateRequest createRequest){
        var categoryStr = createRequest.getCategoryStr();
        Set<Category> categories = new HashSet<>();

        var productDomain = productMapper.entityToDomain(createRequest);
        if (StringUtils.isEmpty(categoryStr)){
            throw new NotFoundException(CATEGORY_CANT_BE_EMPTY);
        } else {
            CategoryEntity category = categoryRepository.findByCategoryName(categoryStr)
                    .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND));
            categories.add(categoryMapper.entityToDomain(category));
        }
        return productDomain.setCategories(categories);
    }*/


    public void updateOne(UUID id, ProductUpdateRequest updateRequest) {
        var entity = productRepository.findById(id).orElseThrow(()->new NotFoundException(PRODUCT_NOT_FOUND));

        productRepository.save(entity);
    }

    //@Override
    public void deleteOne(UUID id) {
        productRepository.deleteById(id);
    }
}
