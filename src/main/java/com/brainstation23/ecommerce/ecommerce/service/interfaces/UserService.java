package com.brainstation23.ecommerce.ecommerce.service.interfaces;

import com.brainstation23.ecommerce.ecommerce.model.domain.User;
import com.brainstation23.ecommerce.ecommerce.model.dto.user.ChangePasswordRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.user.UserCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.user.UserSignInRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.user.UserUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.OrderEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface UserService {
    Page<User> getAll(Pageable pageable);
    User getOne(UUID id);
    UUID createOne(UserCreateRequest createRequest);
    void updateOne(UUID id, UserUpdateRequest updateRequest);
    void changePassword(UUID id, ChangePasswordRequest changePasswordRequest);
    void deleteOne(UUID id);
    UserEntity signIn(UserSignInRequest signInRequest);
    UserEntity getSessionUser();
    List<OrderEntity> getAllOrdersByUser(UUID userId);
    void clearCart(UUID userId);
}
