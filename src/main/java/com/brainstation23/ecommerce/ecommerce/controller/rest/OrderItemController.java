package com.brainstation23.ecommerce.ecommerce.controller.rest;

import com.brainstation23.ecommerce.ecommerce.mapper.OrderItemMapper;
import com.brainstation23.ecommerce.ecommerce.model.dto.order.OrderResponse;
import com.brainstation23.ecommerce.ecommerce.model.dto.orderItem.OrderItemCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.orderItem.OrderItemUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.OrderItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@Tag(name = "Order Item")
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "api/order_items")
public class OrderItemController {
    private final OrderItemService orderItemService;
    private final OrderItemMapper orderItemMapper;

    @Operation(summary = "Getting All order items")
    @GetMapping("")
    public ResponseEntity<Page<OrderResponse>> getAll(Pageable pageable)
    {
        log.info("Getting List of order items");
        var entities = orderItemService.getAll(pageable);
        return ResponseEntity.ok(entities.map(orderItemMapper::domainToResponse));
    }

    @Operation(summary = "Getting order Item By Id")
    @GetMapping("{id}")
    public ResponseEntity<OrderResponse> getById(@PathVariable UUID id)
    {
        log.info("Getting Details of order Item ({})", id);
        var entity = orderItemService.getOne(id);
        return ResponseEntity.ok(orderItemMapper.domainToResponse(entity));
    }

    @Operation(summary = "Creating New Single Order Item ")
    @PostMapping
    public ResponseEntity<Void> createOne(@RequestBody @Valid OrderItemCreateRequest createRequest) {
        log.info("Creating a Order  Item : {} ", createRequest);
        var id = orderItemService.createOne(createRequest);
        var location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Update Single Order  Item ")
    @PutMapping("{id}")
    public ResponseEntity<Void> updateOne(@PathVariable UUID id,
                                          @RequestBody @Valid OrderItemUpdateRequest updateRequest) {
        log.info("Updating a order  Item ({}): {} ", id, updateRequest);
        orderItemService.updateOne(id, updateRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete Single Order  Item ")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable UUID id) {
        log.info("Deleting an Order  Item ({}) ", id);
        orderItemService.deleteOne(id);
        return ResponseEntity.noContent().build();
    }
}
