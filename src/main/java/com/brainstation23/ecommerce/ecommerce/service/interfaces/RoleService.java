package com.brainstation23.ecommerce.ecommerce.service.interfaces;

import com.brainstation23.ecommerce.ecommerce.model.domain.Role;
import com.brainstation23.ecommerce.ecommerce.model.dto.role.RoleCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.role.RoleUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface RoleService {
    Page<Role> getAll(Pageable pageable);
    Role getOne(UUID id);
    UUID createOne(RoleCreateRequest createRequest);
    void updateOne(UUID id, RoleUpdateRequest updateRequest);
    void deleteOne(UUID id);
}
