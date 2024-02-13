package com.brainstation23.ecommerce.ecommerce.service.impl;

import com.brainstation23.ecommerce.ecommerce.mapper.UserMapper;
import com.brainstation23.ecommerce.ecommerce.model.domain.User;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.UserEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.repository.UserRepository;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.UserStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Scope("prototype")
public class UserStatusImpl implements UserStatus {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public Model loginStatus(Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<UserEntity> user = userRepository.findByUsername(authentication.getName());
        if(user.isPresent() && authentication.isAuthenticated())
        {
            model.addAttribute("isLoggedIn", true);
        }
        else
        {
            model.addAttribute("isLoggedIn", false);
        }
        return model;
    }

    @Override
    public Optional<UserEntity> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(authentication.getName());
    }

}
