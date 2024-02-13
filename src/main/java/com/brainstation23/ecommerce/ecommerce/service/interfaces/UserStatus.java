package com.brainstation23.ecommerce.ecommerce.service.interfaces;

import com.brainstation23.ecommerce.ecommerce.model.domain.User;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import java.util.Optional;

public interface UserStatus {
    Model loginStatus(Model model);
    Optional<UserEntity> getCurrentUser();
}
