package com.brainstation23.ecommerce.ecommerce.service.impl;

import com.brainstation23.ecommerce.ecommerce.exception.custom.NotFoundException;
import com.brainstation23.ecommerce.ecommerce.mapper.RoleMapper;
import com.brainstation23.ecommerce.ecommerce.model.domain.Role;
import com.brainstation23.ecommerce.ecommerce.model.dto.role.RoleCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.role.RoleUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.RoleEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.repository.RoleRepository;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{
    private static  final String ROLE_NOT_FOUND = "Role Not Found";
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public Page<Role> getAll(Pageable pageable) {
        var entities = roleRepository.findAll(pageable);
        return entities.map(roleMapper::entityToDomain);
    }

    @Override
    public Role getOne(UUID id) {
        var entity = roleRepository.findById(id).orElseThrow(()->new NotFoundException(ROLE_NOT_FOUND));
        return roleMapper.entityToDomain(entity);
    }

    @Override
    public UUID createOne(RoleCreateRequest createRequest) {
        var entity = new RoleEntity();
        entity.setName(createRequest.getName());
        var createdEntity = roleRepository.save(entity);
        return createdEntity.getId();
    }

    @Override
    public void updateOne(UUID id, RoleUpdateRequest updateRequest) {
        var entity = roleRepository.findById(id).orElseThrow(()->new NotFoundException(ROLE_NOT_FOUND));
        entity.setName(updateRequest.getName());
        roleRepository.save(entity);
    }

    @Override
    public void deleteOne(UUID id) {
        roleRepository.deleteById(id);
    }
}
