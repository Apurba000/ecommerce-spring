package com.brainstation23.ecommerce.ecommerce.controller.web;

import com.brainstation23.ecommerce.ecommerce.constant.OtherConstants;
import com.brainstation23.ecommerce.ecommerce.model.dto.user.ChangePasswordRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.user.UserUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.UserService;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.UserStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping(UserUpdateAndDetailsController.USER_DETAILS)
public class UserUpdateAndDetailsController {
    private final UserService userService;
    public static final String USER_DETAILS = "/userdetails";
    private static final String ATTRIBUTE_USER = "user";
    private static final String REDIRECT_USER = "redirect:/useretails";
    private final UserStatus userStatus;
    @GetMapping
    public String getUserDetails(Model model) {
        var user = userService.getSessionUser();
        if (user == null)
        {
            return OtherConstants.signIn;
        };
        userStatus.loginStatus(model);
        model.addAttribute(ATTRIBUTE_USER, user);
        model.addAttribute("pageTitle", "User Details");
        model.addAttribute("content", "user/usercrud/userdetails");
        return "user/base";
    }
    @GetMapping("/update")
    public String updateUser( Model model) {
        var user = userService.getSessionUser();
        if (user == null)
        {
            return OtherConstants.signIn;
        };
        userStatus.loginStatus(model);
        model.addAttribute(ATTRIBUTE_USER, user);
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
        var user = userService.getSessionUser();
        if (user == null)
        {
            return OtherConstants.signIn;
        };
        userStatus.loginStatus(model);
        model.addAttribute("pageTitle", "Change Password");
        model.addAttribute("id", user.getId());
        model.addAttribute("content", "user/usercrud/changepass");
        return "user/base";
    }
    @PostMapping("/updatepassword")
    public String updateUser(@ModelAttribute(ATTRIBUTE_USER) ChangePasswordRequest passwordChangeRequest) {
        var user = userService.getSessionUser();
        userService.changePassword(user.getId(),passwordChangeRequest);
        return REDIRECT_USER;
    }

    @GetMapping("/getorders")
    public String getOrdersByUserId(Model model)
    {
        var currentUser = userService.getSessionUser();
        if (currentUser == null)
        {
            return OtherConstants.signIn;
        };
        userStatus.loginStatus(model);
        var orders = userService.getAllOrdersByUser(currentUser.getId());
        model.addAttribute("pageTitle", "Orders");
        model.addAttribute("order_list", orders);
        model.addAttribute("content", "user/order/index");
        return "user/base";
    }
}
