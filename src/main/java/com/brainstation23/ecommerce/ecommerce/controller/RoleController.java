package com.brainstation23.ecommerce.ecommerce.controller;

import com.brainstation23.ecommerce.ecommerce.mapper.RoleMapper;
import com.brainstation23.ecommerce.ecommerce.model.dto.role.RoleCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.role.RoleResponse;
import com.brainstation23.ecommerce.ecommerce.model.dto.role.RoleUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.RoleService;
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
@RequestMapping(value = "api/roles")
public class RoleController {
    private final RoleService roleService;
    private final RoleMapper roleMapper;

    @Operation(summary = "Getting All Roles")
    @GetMapping("")
    public ResponseEntity<Page<RoleResponse>> getAll(Pageable pageable)
    {
        log.info("Getting List of roles");
        var entities = roleService.getAll(pageable);
        return ResponseEntity.ok(entities.map(roleMapper::domainToResponse));
    }

    @Operation(summary = "Getting role By Id")
    @GetMapping("{id}")
    public ResponseEntity<RoleResponse> getById(@PathVariable UUID id)
    {
        log.info("Getting Details of Role({})", id);
        var entity = roleService.getOne(id);
        return ResponseEntity.ok(roleMapper.domainToResponse(entity));
    }

    @Operation(summary = "Creating New Single Role")
    @PostMapping
    public ResponseEntity<Void> createOne(@RequestBody @Valid RoleCreateRequest createRequest) {
        log.info("Creating a Role : {} ", createRequest);
        var id = roleService.createOne(createRequest);
        var location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Update Single Role")
    @PutMapping("{id}")
    public ResponseEntity<Void> updateOne(@PathVariable UUID id,
                                          @RequestBody @Valid RoleUpdateRequest updateRequest) {
        log.info("Updating a Role ({}): {} ", id, updateRequest);
        roleService.updateOne(id, updateRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete Single Role")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable UUID id) {
        log.info("Deleting a Role ({}) ", id);
        roleService.deleteOne(id);
        return ResponseEntity.noContent().build();
    }
}
