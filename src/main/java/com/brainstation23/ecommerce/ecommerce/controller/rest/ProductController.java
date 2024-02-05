package com.brainstation23.ecommerce.ecommerce.controller.rest;

import com.brainstation23.ecommerce.ecommerce.mapper.ProductMapper;
import com.brainstation23.ecommerce.ecommerce.model.dto.product.ProductCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.product.ProductResponse;
import com.brainstation23.ecommerce.ecommerce.model.dto.product.ProductUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.service.impl.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@Tag(name = "Product")
@Slf4j
@RestController
@RequestMapping(value = "api/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;
    @Autowired
    public ProductController(ProductService productService, ProductMapper productMapper)
    {
        this.productService = productService;
        this.productMapper = productMapper;
    }



    @Operation(summary = "Getting All Products")
    @GetMapping("")
    public ResponseEntity<Page<ProductResponse>> getAll(Pageable pageable)
    {
        log.info("Getting List of products");
        var entities = productService.getAll(pageable);
        return ResponseEntity.ok(entities.map(productMapper::domainToResponse));
    }

    /*@Operation(summary = "Getting Product By Id")
    @GetMapping("{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable UUID id)
    {
        log.info("Getting Details of Product({})", id);
        var entity = productService.getOne(id);
        return ResponseEntity.ok(productMapper.domainToResponse(entity));
    }*/

    @Operation(summary = "Creating New Single Product")
    @PostMapping
    public ResponseEntity<Void> createOne(@RequestBody @Valid ProductCreateRequest createRequest) {
        log.info("Creating a Product : {} ", createRequest);
        var id = productService.createOne(createRequest);
        var location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    /*@Operation(summary = "Update Single Product")
    @PutMapping("{id}")
    public ResponseEntity<Void> updateOne(@PathVariable UUID id,
                                          @RequestBody @Valid ProductUpdateRequest updateRequest) {
        log.info("Updating a Product ({}): {} ", id, updateRequest);
        productService.updateOne(id, updateRequest);
        return ResponseEntity.noContent().build();
    }
*/
    /*@Operation(summary = "Delete Single Product")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable UUID id) {
        log.info("Deleting a product ({}) ", id);
        productService.deleteOne(id);
        return ResponseEntity.noContent().build();
    }*/
}
