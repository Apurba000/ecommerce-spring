package com.brainstation23.ecommerce.ecommerce.controller.rest;

import com.brainstation23.ecommerce.ecommerce.mapper.CartItemMapper;
import com.brainstation23.ecommerce.ecommerce.model.dto.cartItem.CartItemCreateUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.cartItem.CartItemResponse;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.CartItemService;
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

@PreAuthorize("hasRole('USER')")
@Tag(name = "Cart Item")
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "api/cart_items")
public class CartItemController {
    private final CartItemService cartItemService;
    private final CartItemMapper cartItemMapper;

    @Operation(summary = "Getting All Cart Items")
    @GetMapping("")
    public ResponseEntity<Page<CartItemResponse>> getAll(Pageable pageable)
    {
        log.info("Getting List of cart items");
        var entities = cartItemService.getAll(pageable);
        return ResponseEntity.ok(entities.map(cartItemMapper::domainToResponse));
    }

    @Operation(summary = "Getting Cart Item By Id")
    @GetMapping("{id}")
    public ResponseEntity<CartItemResponse> getById(@PathVariable UUID id)
    {
        log.info("Getting Details of cart Item({})", id);
        var entity = cartItemService.getOne(id);
        return ResponseEntity.ok(cartItemMapper.domainToResponse(entity));
    }

    @Operation(summary = "Creating New Single Cart Item")
    @PostMapping
    public ResponseEntity<Void> createOne(@RequestBody @Valid CartItemCreateUpdateRequest createRequest) {
        log.info("Creating a Cart Item: {} ", createRequest);
        var id = cartItemService.createOne(createRequest);
        var location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Update Single Cart Item")
    @PutMapping("{id}")
    public ResponseEntity<Void> updateOne(@PathVariable UUID id,
                                          @RequestBody @Valid CartItemCreateUpdateRequest updateRequest) {
        log.info("Updating a Cart Item({}): {} ", id, updateRequest);
        cartItemService.updateOne(updateRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete Single CartItem")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable UUID id) {
        log.info("Deleting a Cart Item({}) ", id);
        cartItemService.deleteOne(id);
        return ResponseEntity.noContent().build();
    }
}
