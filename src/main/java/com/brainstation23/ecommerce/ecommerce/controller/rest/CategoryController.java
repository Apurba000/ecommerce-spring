package com.brainstation23.ecommerce.ecommerce.controller.rest;

import com.brainstation23.ecommerce.ecommerce.mapper.CategoryMapper;
import com.brainstation23.ecommerce.ecommerce.model.dto.category.CategoryCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.category.CategoryResponse;
import com.brainstation23.ecommerce.ecommerce.model.dto.category.CategoryUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Category")
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Operation(summary = "Getting All Categories")
    @GetMapping("")
    public ResponseEntity<Page<CategoryResponse>> getAll(Pageable pageable)
    {
        log.info("Getting List of categories");
        var entities = categoryService.getAll(pageable);
        return ResponseEntity.ok(entities.map(categoryMapper::domainToResponse));
    }

    @Operation(summary = "Getting category Item By Id")
    @GetMapping("{id}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable UUID id)
    {
        log.info("Getting Details of category({})", id);
        var entity = categoryService.getOne(id);
        return ResponseEntity.ok(categoryMapper.domainToResponse(entity));
    }

    @Operation(summary = "Creating New Single Category")
    @PostMapping
    public ResponseEntity<Void> createOne(@RequestBody @Valid CategoryCreateRequest createRequest) {
        log.info("Creating a Category: {} ", createRequest);
        var id = categoryService.createOne(createRequest);
        var location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Update Single category")
    @PutMapping("{id}")
    public ResponseEntity<Void> updateOne(@PathVariable UUID id,
                                          @RequestBody @Valid CategoryUpdateRequest updateRequest) {
        log.info("Updating a Category({}): {} ", id, updateRequest);
        categoryService.updateOne(id, updateRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete Single CartItem")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable UUID id) {
        log.info("Deleting an Category({}) ", id);
        categoryService.deleteOne(id);
        return ResponseEntity.noContent().build();
    }
}
