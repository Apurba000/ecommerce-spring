package com.brainstation23.ecommerce.ecommerce.controller.rest;

import com.brainstation23.ecommerce.ecommerce.mapper.OrderMapper;
import com.brainstation23.ecommerce.ecommerce.model.dto.order.OrderCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.order.OrderResponse;
import com.brainstation23.ecommerce.ecommerce.model.dto.order.OrderUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.OrderService;
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
@Tag(name = "Order")
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "api/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Operation(summary = "Getting All orders")
    @GetMapping("")
    public ResponseEntity<Page<OrderResponse>> getAll(Pageable pageable)
    {
        log.info("Getting List of orders");
        var entities = orderService.getAll(pageable);
        return ResponseEntity.ok(entities.map(orderMapper::domainToResponse));
    }

    @Operation(summary = "Getting order By Id")
    @GetMapping("{id}")
    public ResponseEntity<OrderResponse> getById(@PathVariable UUID id)
    {
        log.info("Getting Details of order({})", id);
        var entity = orderService.getOne(id);
        return ResponseEntity.ok(orderMapper.domainToResponse(entity));
    }

    @Operation(summary = "Creating New Single Order")
    @PostMapping
    public ResponseEntity<Void> createOne(@RequestBody @Valid OrderCreateRequest createRequest) {
        log.info("Creating a Order: {} ", createRequest);
        var id = orderService.createOne(createRequest);
        var location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Update Single Order")
    @PutMapping("{id}")
    public ResponseEntity<Void> updateOne(@PathVariable UUID id,
                                          @RequestBody @Valid OrderUpdateRequest updateRequest) {
        log.info("Updating a order({}): {} ", id, updateRequest);
        orderService.updateOne(id, updateRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete Single Order")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable UUID id) {
        log.info("Deleting an Order({}) ", id);
        orderService.deleteOne(id);
        return ResponseEntity.noContent().build();
    }
}
