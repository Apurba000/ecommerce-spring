package com.brainstation23.ecommerce.ecommerce.service.impl;

import com.brainstation23.ecommerce.ecommerce.exception.custom.NotFoundException;
import com.brainstation23.ecommerce.ecommerce.mapper.ProductMapper;
import com.brainstation23.ecommerce.ecommerce.model.domain.Product;
import com.brainstation23.ecommerce.ecommerce.model.dto.product.ProductCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.product.ProductUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.ProductEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.repository.ProductRepository;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private static final String PRODUCT_NOT_FOUND = "Order Not Found";
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Page<Product> getAll(Pageable pageable) {
        var entities = productRepository.findAll(pageable);
        return entities.map(productMapper::entityToDomain);
    }

    @Override
    public Product getOne(UUID id) {
        var entity = productRepository.findById(id).orElseThrow(()->new NotFoundException(PRODUCT_NOT_FOUND));
        return productMapper.entityToDomain(entity);
    }

    @Override
    public UUID createOne(ProductCreateRequest createRequest) {
        var entity = new ProductEntity();

        var createdEntity = productRepository.save(entity);
        return createdEntity.getId();
    }

    @Override
    public void updateOne(UUID id, ProductUpdateRequest updateRequest) {
        var entity = productRepository.findById(id).orElseThrow(()->new NotFoundException(PRODUCT_NOT_FOUND));

        productRepository.save(entity);
    }

    @Override
    public void deleteOne(UUID id) {
        productRepository.deleteById(id);
    }
}
