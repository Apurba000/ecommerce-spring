package com.brainstation23.ecommerce.ecommerce.service.impl;

import com.brainstation23.ecommerce.ecommerce.exception.custom.NotFoundException;
import com.brainstation23.ecommerce.ecommerce.mapper.UserMapper;
import com.brainstation23.ecommerce.ecommerce.model.domain.User;
import com.brainstation23.ecommerce.ecommerce.model.dto.user.UserCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.user.UserUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.UserEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.repository.UserRepository;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private static final String USER_NOT_FOUND = "User Not Found";
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Page<User> getAll(Pageable pageable) {
        var entities = userRepository.findAll(pageable);
        return entities.map(userMapper::entityToDomain);
    }

    @Override
    public User getOne(UUID id) {
        var entity = userRepository.findById(id).orElseThrow(()->new NotFoundException(USER_NOT_FOUND));
        return userMapper.entityToDomain(entity);
    }

    @Override
    public UUID createOne(UserCreateRequest createRequest) {
        var entity = new UserEntity();
        var createdEntity = userRepository.save(entity);
        return createdEntity.getId();
    }

    @Override
    public void updateOne(UUID id, UserUpdateRequest updateRequest) {
        var entity = userRepository.findById(id).orElseThrow(()->new NotFoundException(USER_NOT_FOUND));
        userRepository.save(entity);
    }

    @Override
    public void deleteOne(UUID id) {
        userRepository.deleteById(id);
    }
}
