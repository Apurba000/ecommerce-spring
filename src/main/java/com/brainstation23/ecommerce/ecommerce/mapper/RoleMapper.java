package com.brainstation23.ecommerce.ecommerce.mapper;

import com.brainstation23.ecommerce.ecommerce.model.domain.Role;
import com.brainstation23.ecommerce.ecommerce.model.dto.RoleResponse;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role entityToDomain(RoleEntity entity);
    RoleResponse domainToResponse(Role role);
}
