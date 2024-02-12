package com.brainstation23.ecommerce.ecommerce.service.impl;

import com.brainstation23.ecommerce.ecommerce.constant.OtherConstants;
import com.brainstation23.ecommerce.ecommerce.model.domain.User;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.UserService;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.UserStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@Slf4j
@RequiredArgsConstructor
@Scope("prototype")
public class UserStatusImpl implements UserStatus {
    private final UserService userService;
    @Override
    public Model loginStatus(Model model)
    {
        var user = userService.getSessionUser();
        if(user == null)
        {
            model.addAttribute("isLoggedIn", false);
        }
        else
        {
            model.addAttribute("isLoggedIn", true);
        }
        return model;
    }

}
