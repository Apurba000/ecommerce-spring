package com.brainstation23.ecommerce.ecommerce.controller.web;

import com.brainstation23.ecommerce.ecommerce.constant.OtherConstants;
import com.brainstation23.ecommerce.ecommerce.exception.custom.NotFoundException;
import com.brainstation23.ecommerce.ecommerce.mapper.UserMapper;
import com.brainstation23.ecommerce.ecommerce.model.dto.user.ChangePasswordRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.user.UserUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.UserEntity;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.UserService;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.UserStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//@PreAuthorize("hasRole('USER')")
@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping(UserUpdateAndDetailsController.USER_DETAILS)
public class UserUpdateAndDetailsController {
    private final UserService userService;
    public static final String USER_DETAILS = "/user/details";
    private static final String ATTRIBUTE_USER = "user";
    private static final String REDIRECT_USER = "redirect:"+USER_DETAILS;
    private final UserStatus userStatus;
    private final UserMapper userMapper;
    @GetMapping
    public String getUserDetails(Model model) {
        var userEntity = userStatus.getCurrentUser();
        if (userEntity.isEmpty())
        {
            return OtherConstants.signIn;
        };

        userStatus.loginStatus(model);
        model.addAttribute(ATTRIBUTE_USER, userMapper.entityToDomain(userEntity.get()));
        model.addAttribute("pageTitle", "User Details");
        model.addAttribute("content", "user/usercrud/userdetails");
        return "user/base";
    }
    @GetMapping("/update")
    public String updateUser( Model model) {
        var userEntity = userStatus.getCurrentUser();
        if (userEntity.isEmpty())
        {
            return OtherConstants.signIn;
        };

        userStatus.loginStatus(model);
        model.addAttribute(ATTRIBUTE_USER, userMapper.entityToDomain(userEntity.get()));
        model.addAttribute("pageTitle", "Update User");
        model.addAttribute("content", "user/usercrud/update_user");
        return "user/base";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute(ATTRIBUTE_USER) UserUpdateRequest userUpdateRequest) {
        userService.updateOne(userUpdateRequest.getId(),userUpdateRequest);
        return REDIRECT_USER;
    }

    @GetMapping("/passwordchange")
    public String passwordChange(Model model) {
        var userOptional = userStatus.getCurrentUser();
        if (userOptional.isEmpty())
        {
            return OtherConstants.signIn;
        };
        UserEntity user = userOptional.get();
        userStatus.loginStatus(model);
        model.addAttribute("pageTitle", "Change Password");
        model.addAttribute("id", user.getId());
        model.addAttribute("content", "user/usercrud/changepass");
        return "user/base";
    }
    @PostMapping("/updatepassword")
    public String updateUser(@ModelAttribute(ATTRIBUTE_USER) ChangePasswordRequest passwordChangeRequest) {
        var userOptional = userStatus.getCurrentUser();
        if (userOptional.isEmpty())
        {
            return OtherConstants.signIn;
        };
        UserEntity user = userOptional.get();
        userService.changePassword(user.getId(),passwordChangeRequest);
        return REDIRECT_USER;
    }

    @GetMapping("/getorders")
    public String getOrdersByUserId(Model model)
    {
        var userOptional = userStatus.getCurrentUser();
        if (userOptional.isEmpty()) {
            return OtherConstants.signIn;
        }
        UserEntity user = userOptional.get(); // Retrieve the user from the Optional
        userStatus.loginStatus(model);
        var orders = userService.getAllOrdersByUser(user.getId());
        model.addAttribute("pageTitle", "Orders");
        model.addAttribute("order_list", orders);
        model.addAttribute("content", "user/order/index");
        return "user/base";
    }


}
