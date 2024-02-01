package com.brainstation23.ecommerce.ecommerce.service.interfaces;

import com.brainstation23.ecommerce.ecommerce.model.domain.Role;
import com.brainstation23.ecommerce.ecommerce.model.dto.role.RoleCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.role.RoleUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {
    Page<Role> getAll(Pageable pageable);
    Role getOne(Long id);
    Long createOne(RoleCreateRequest createRequest);
    void updateOne(Long id, RoleUpdateRequest updateRequest);
    void deleteOne(Long id);
}
