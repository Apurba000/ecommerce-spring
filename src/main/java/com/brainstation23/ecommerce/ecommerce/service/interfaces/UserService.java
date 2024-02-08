package com.brainstation23.ecommerce.ecommerce.service.interfaces;

import com.brainstation23.ecommerce.ecommerce.model.domain.User;
import com.brainstation23.ecommerce.ecommerce.model.dto.user.UserCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.user.UserSignInRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.user.UserUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {
    Page<User> getAll(Pageable pageable);
    User getOne(UUID id);
    UUID createOne(UserCreateRequest createRequest);
    void updateOne(UUID id, UserUpdateRequest updateRequest);
    void deleteOne(UUID id);
    UserEntity signIn(UserSignInRequest signInRequest);
    UserEntity getSessionUser();
}
