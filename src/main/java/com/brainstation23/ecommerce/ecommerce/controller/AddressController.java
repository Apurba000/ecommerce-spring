package com.brainstation23.ecommerce.ecommerce.controller;

import com.brainstation23.ecommerce.ecommerce.mapper.AddressMapper;
import com.brainstation23.ecommerce.ecommerce.model.dto.Address.AddressCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.Address.AddressResponse;
import com.brainstation23.ecommerce.ecommerce.model.dto.Address.AddressUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "api/addresses")
public class AddressController {
    private final AddressService addressService;
    private final AddressMapper addressMapper;

    @Operation(summary = "Getting All Addresses")
    @GetMapping("")
    public ResponseEntity<Page<AddressResponse>> getAll(Pageable pageable)
    {
        log.info("Getting List of Addresses");
        var entities = addressService.getAll(pageable);
        return ResponseEntity.ok(entities.map(addressMapper::domainToResponse));
    }

    @Operation(summary = "Getting Address By Id")
    @GetMapping("{id}")
    public ResponseEntity<AddressResponse> getById(@PathVariable UUID id)
    {
        log.info("Getting Details of Address({})", id);
        var entity = addressService.getOne(id);
        return ResponseEntity.ok(addressMapper.domainToResponse(entity));
    }

    @Operation(summary = "Creating New Single Address")
    @PostMapping
    public ResponseEntity<Void> createOne(@RequestBody @Valid AddressCreateRequest createRequest) {
        log.info("Creating an Address: {} ", createRequest);
        var id = addressService.createOne(createRequest);
        var location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Update Single Address")
    @PutMapping("{id}")
    public ResponseEntity<Void> updateOne(@PathVariable UUID id,
                                          @RequestBody @Valid AddressUpdateRequest updateRequest) {
        log.info("Updating an Address({}): {} ", id, updateRequest);
        addressService.updateOne(id, updateRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete Single Address")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable UUID id) {
        log.info("Deleting an address({}) ", id);
        addressService.deleteOne(id);
        return ResponseEntity.noContent().build();
    }
}
