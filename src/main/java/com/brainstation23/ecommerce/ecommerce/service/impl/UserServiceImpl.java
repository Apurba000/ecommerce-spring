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
import com.brainstation23.ecommerce.ecommerce.model.security.SecureUserDetails;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private static final String USER_NOT_FOUND = "User Not Found";
    private static final String USER_NOT_FOUND_WITH_USERNAME = "User Not Found with username: ";
    private static final String INVALID_CRED = "Invalid Credentials!";
    private static final String WRONG_PASSWORD = "Wrong Password";
    private static final String PASSWORD_NOT_MATCH = "Password Not Matched";

    private static final String SESSION_USER_ATTRIBUTE = "user";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final HttpSession httpSession;

    private final AddressMapper addressMapper;

    private PasswordEncoder passwordEncoder;

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
        var role = roleRepository.findByName(ERole.ROLE_CUSTOMER).orElseThrow(()-> new NotFoundException("ROLE NOT FOUND"));
        var roles = new HashSet<RoleEntity>();
        roles.add(role);
        entity.setFirstname(createRequest.getFirstname())
                .setLastname(createRequest.getLastname())
                .setUsername(createRequest.getUsername())
                .setEmail(createRequest.getEmail())
                .setPassword(getEncryptedPassword(createRequest.getPassword()))
                .setPhone(createRequest.getPhone())
                .setRoles(roles);
        var createdEntity = userRepository.save(entity);
        return createdEntity.getId();
    }

    private String getEncryptedPassword(String rawPassword){
        if (passwordEncoder == null)
            passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(rawPassword);
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
        String oldPassEntered = changePasswordRequest.getOldPassword();
        String oldPassEncoded = entity.getPassword();
        if (!passwordEncoder.matches(oldPassEntered, oldPassEncoded)) throw new NotFoundException(INVALID_CRED);

        String newPassword = changePasswordRequest.getNewPassword();
        String confirmedPassword = changePasswordRequest.getConfirmPassword();
        if (!StringUtils.equals(newPassword, confirmedPassword)) throw new NotFoundException(PASSWORD_NOT_MATCH);

        entity.setPassword(getEncryptedPassword(newPassword));
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
    public void logOut() {
        httpSession.setAttribute(SESSION_USER_ATTRIBUTE, null);
    }

    @Override
    public SecureUserDetails loadUserByUsername(String username){
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_WITH_USERNAME + username));

        return SecureUserDetails.build(user);
    }

    @Override
    public UserEntity signIn(UserSignInRequest signInRequest) {
        return temporarySignIn(signInRequest);
    }

    @Override
    public UserEntity getSessionUser() {
        return (UserEntity) httpSession.getAttribute(SESSION_USER_ATTRIBUTE);
    }


    @Override
    public List<OrderEntity> getAllOrdersByUser(UUID userId) {
        var entity = userRepository.findById(userId).orElseThrow(()->new NotFoundException(USER_NOT_FOUND));
        return entity.getOrders();
    }

    private UserEntity temporarySignIn(UserSignInRequest signInRequest){
        UserEntity user = userRepository.findByUsername(signInRequest.getUsername())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));

        String rawPassWord = signInRequest.getPassword();
        String encryptedPassword = user.getPassword();
        if (passwordEncoder == null) passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(rawPassWord, encryptedPassword)) throw new NotFoundException(INVALID_CRED);

        httpSession.setAttribute(SESSION_USER_ATTRIBUTE, user);
        return user;
    }

}
