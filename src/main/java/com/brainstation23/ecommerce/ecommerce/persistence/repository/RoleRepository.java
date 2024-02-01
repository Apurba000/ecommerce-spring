package com.brainstation23.ecommerce.ecommerce.persistence.repository;

import com.brainstation23.ecommerce.ecommerce.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
}
