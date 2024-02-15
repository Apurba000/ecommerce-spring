package com.brainstation23.ecommerce.ecommerce.controller.rest;

import com.brainstation23.ecommerce.ecommerce.mapper.UserMapper;
import com.brainstation23.ecommerce.ecommerce.model.dto.user.*;
import com.brainstation23.ecommerce.ecommerce.model.security.SecureUserDetails;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.UserService;
import com.brainstation23.ecommerce.ecommerce.util.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Tag(name = "User")
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "api/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

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


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserSignInRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        SecureUserDetails userDetails = (SecureUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtSignInResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping("/test")
    public String customerAccess() {
        return "Hello Customer.  !!!!!";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/test2")
    public String customerAccessTest() {
        return "Hello ADMIN.  !!!!!";
    }

}
