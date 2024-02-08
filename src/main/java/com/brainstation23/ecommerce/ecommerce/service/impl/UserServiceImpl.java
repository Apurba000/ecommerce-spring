package com.brainstation23.ecommerce.ecommerce.service.impl;

import com.brainstation23.ecommerce.ecommerce.exception.custom.NotFoundException;
import com.brainstation23.ecommerce.ecommerce.mapper.UserMapper;
import com.brainstation23.ecommerce.ecommerce.model.domain.User;
import com.brainstation23.ecommerce.ecommerce.model.dto.user.UserCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.user.UserSignInRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.user.UserUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.model.enums.ERole;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.RoleEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.UserEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.repository.RoleRepository;
import com.brainstation23.ecommerce.ecommerce.persistence.repository.UserRepository;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private static final String USER_NOT_FOUND = "User Not Found";
    private static final String INVALID_CRED = "Invalid Credentials!";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final HttpSession httpSession;

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
        var role = roleRepository.findByName(ERole.USER).orElseThrow(()-> new NotFoundException("ROLE NOT FOUND"));
        var roles = new HashSet<RoleEntity>();
        roles.add(role);
        entity.setFirstname(createRequest.getFirstname())
                .setLastname(createRequest.getLastname())
                .setUsername(createRequest.getUsername())
                .setEmail(createRequest.getEmail())
                .setPassword(createRequest.getPassword())
                .setPhone(createRequest.getPhone())
                .setRoles(roles);
        var createdEntity = userRepository.save(entity);
        return createdEntity.getId();
    }

    @Override
    public void updateOne(UUID id, UserUpdateRequest updateRequest) {
        var entity = userRepository.findById(id).orElseThrow(()->new NotFoundException(USER_NOT_FOUND));
        entity.setFirstname(updateRequest.getFirstname())
                .setLastname(updateRequest.getLastname())
                .setUsername(updateRequest.getUsername())
                .setEmail(updateRequest.getEmail())
                .setPassword(updateRequest.getPassword())
                .setPhone(updateRequest.getPhone())
                .setRoles(updateRequest.getRoles())
                .setAddress(updateRequest.getAddress())
                .setCartItems(updateRequest.getCartItems());
        userRepository.save(entity);
    }

    @Override
    public void deleteOne(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserEntity signIn(UserSignInRequest signInRequest) {
        return temporarySignIn(signInRequest);
    }

    @Override
    public UserEntity getSessionUser() {
        return (UserEntity) httpSession.getAttribute("user");
    }

    private UserEntity temporarySignIn(UserSignInRequest signInRequest){
        UserEntity user = (UserEntity) httpSession.getAttribute("user");
        if (user == null) user = userRepository.findByUsername(signInRequest.getUsername())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));

        if (!StringUtils.equals(user.getPassword(), signInRequest.getPassword()))
            throw new NotFoundException(INVALID_CRED);

        httpSession.setAttribute("user", user);
        return user;
    }
}
