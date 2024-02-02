package com.brainstation23.ecommerce.ecommerce.service.impl;

import com.brainstation23.ecommerce.ecommerce.mapper.ProductMapper;
import com.brainstation23.ecommerce.ecommerce.model.domain.Product;
import com.brainstation23.ecommerce.ecommerce.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private static final String PRODUCT_NOT_FOUND = "Product Not Found";
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    public Page<Product> getAll(Pageable pageable) {
        var entities = productRepository.findAll(pageable);
        return entities.map(productMapper::entityToDomain);
    }

//
//    public Product getOne(Long id) {
//        var entity = productRepository.findById(id).orElseThrow(()->new NotFoundException(PRODUCT_NOT_FOUND));
//        return productMapper.entityToDomain(entity);
//    }
//
//
//    public Long createOne(ProductCreateRequest createRequest) {
//        var entity = new ProductEntity();
//
//        var createdEntity = productRepository.save(entity);
//        return createdEntity.getId();
//    }
//
//    public void updateOne(Long id, ProductUpdateRequest updateRequest) {
//        var entity = productRepository.findById(id).orElseThrow(()->new NotFoundException(PRODUCT_NOT_FOUND));
//
//        productRepository.save(entity);
//    }
//
//    @Override
//    public void deleteOne(Long id) {
//        productRepository.deleteById(id);
//    }
}
