package com.brainstation23.ecommerce.ecommerce.persistence.repository;

import com.brainstation23.ecommerce.ecommerce.model.enums.ERole;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
    Optional<RoleEntity> findByName(ERole roleName);
}
