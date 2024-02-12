package com.brainstation23.ecommerce.ecommerce.service.impl;

import com.brainstation23.ecommerce.ecommerce.exception.custom.NotFoundException;
import com.brainstation23.ecommerce.ecommerce.mapper.AddressMapper;
import com.brainstation23.ecommerce.ecommerce.mapper.UserMapper;
import com.brainstation23.ecommerce.ecommerce.model.domain.User;
import com.brainstation23.ecommerce.ecommerce.model.dto.user.ChangePasswordRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.user.UserCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.user.UserSignInRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.user.UserUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.model.enums.ERole;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.AddressEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.OrderEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.RoleEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.UserEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.repository.RoleRepository;
import com.brainstation23.ecommerce.ecommerce.persistence.repository.UserRepository;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private static final String USER_NOT_FOUND = "User Not Found";
    private static final String INVALID_CRED = "Invalid Credentials!";
    private static final String WRONG_PASSWORD = "Wrong Password";
    private static final String PASSWORD_NOT_MATCH = "Password Not Matched";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final HttpSession httpSession;

    private final AddressMapper addressMapper;

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
    @Transactional
    public void updateOne(UUID id, UserUpdateRequest updateRequest) {
        var entity = userRepository.findById(id).orElseThrow(()->new NotFoundException(USER_NOT_FOUND));
        List<AddressEntity> addressEntities = updateRequest.getAddress().stream()
                .map(addressMapper::domainToEntity).collect(Collectors.toList());

        entity.setFirstname(updateRequest.getFirstname())
                .setLastname(updateRequest.getLastname())
                .setUsername(updateRequest.getUsername())
                .setEmail(updateRequest.getEmail())
                .setPhone(updateRequest.getPhone())
                .setAddress(addressEntities);
        userRepository.save(entity);
    }

    @Override
    public void changePassword(UUID id, ChangePasswordRequest changePasswordRequest) {
        var entity = userRepository.findById(id).orElseThrow(()->new NotFoundException(USER_NOT_FOUND));
        if(!Objects.equals(entity.getPassword(), changePasswordRequest.getOldPassword()))
        {
            throw new RuntimeException(WRONG_PASSWORD);
        }
        if(!Objects.equals(changePasswordRequest.getConfirmPassword(), changePasswordRequest.getNewPassword()))
        {
            throw new RuntimeException(PASSWORD_NOT_MATCH);
        }
        entity.setPassword(changePasswordRequest.getConfirmPassword());
        userRepository.save(entity);
    }

    @Override
    public void deleteOne(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public void clearCart(UUID userId) {
        var entity = userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException(USER_NOT_FOUND));
        entity.getCartItems().clear();
        userRepository.save(entity);
    }

    @Override
    public UserEntity signIn(UserSignInRequest signInRequest) {
        return temporarySignIn(signInRequest);
    }

    @Override
    public UserEntity getSessionUser() {
        return (UserEntity) httpSession.getAttribute("user");
    }

    @Override
    public List<OrderEntity> getAllOrdersByUser(UUID userId) {
        var entity = userRepository.findById(userId).orElseThrow(()->new NotFoundException(USER_NOT_FOUND));
        return entity.getOrders();
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
