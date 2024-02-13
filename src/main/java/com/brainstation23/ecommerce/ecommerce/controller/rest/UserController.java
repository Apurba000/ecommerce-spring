package com.brainstation23.ecommerce.ecommerce.controller.rest;

import com.brainstation23.ecommerce.ecommerce.mapper.UserMapper;
import com.brainstation23.ecommerce.ecommerce.model.dto.user.UserCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.user.UserResponse;
import com.brainstation23.ecommerce.ecommerce.model.dto.user.UserUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.UserService;
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


@Tag(name = "User")
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "api/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Getting All Users")
    @GetMapping("")
    public ResponseEntity<Page<UserResponse>> getAll(Pageable pageable)
    {
        log.info("Getting List of Users");
        var entities = userService.getAll(pageable);
        return ResponseEntity.ok(entities.map(userMapper::domainToResponse));
    }

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Getting User By Id")
    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable UUID id)
    {
        log.info("Getting Details of User({})", id);
        var entity = userService.getOne(id);
        return ResponseEntity.ok(userMapper.domainToResponse(entity));
    }


    @Operation(summary = "Creating New Single Users")
    @PostMapping
    public ResponseEntity<Void> createOne(@RequestBody @Valid UserCreateRequest createRequest) {
        log.info("Creating a Users : {} ", createRequest);
        var id = userService.createOne(createRequest);
        var location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Update Single User")
    @PutMapping("{id}")
    public ResponseEntity<Void> updateOne(@PathVariable UUID id,
                                          @RequestBody @Valid UserUpdateRequest updateRequest) {
        log.info("Updating a User ({}): {} ", id, updateRequest);
        userService.updateOne(id, updateRequest);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete Single User")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable UUID id) {
        log.info("Deleting a User ({}) ", id);
        userService.deleteOne(id);
        return ResponseEntity.noContent().build();
    }
}
