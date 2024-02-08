package com.brainstation23.ecommerce.ecommerce.controller.web;

import com.brainstation23.ecommerce.ecommerce.model.domain.User;
import com.brainstation23.ecommerce.ecommerce.model.dto.user.ChangePasswordRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.user.UserUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/userupdateanddetails")
public class UserUpdateAndDetailsController {
    private final UserService userService;
    private static final String USER_NOT_FOUND = "User Not Found";
    private static final String ATTRIBUTE_USER = "user";
    private static final String REDIRECT_USER = "redirect:/userupdateanddetails/{id}";
    @GetMapping("/{userId}")
    public String getUserDetails(@PathVariable UUID userId, Model model) {
        User user = userService.getOne(userId);
        model.addAttribute(ATTRIBUTE_USER, user);
        model.addAttribute("pageTitle", "User Details");
        model.addAttribute("content", "user/usercrud/userdetails");
        return "user/base";
    }
    @GetMapping("/update/{userId}")
    public String updateUser(@PathVariable UUID userId, Model model) {
        User user = userService.getOne(userId);
        model.addAttribute(ATTRIBUTE_USER, user);
        model.addAttribute("pageTitle", "Update User");
        model.addAttribute("content", "user/usercrud/update_user");
        return "user/base";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute(ATTRIBUTE_USER) UserUpdateRequest userUpdateRequest) {
        userService.updateOne(userUpdateRequest.getId(),userUpdateRequest);
        return "redirect:/userupdateanddetails/"+userUpdateRequest.getId();
    }

    @GetMapping("/passwordchange/{userId}")
    public String passwordChange(@PathVariable UUID userId, Model model) {
        var user = userService.getOne(userId);
        model.addAttribute("pageTitle", "Change Password");
        model.addAttribute("id", user.getId());
        model.addAttribute("content", "user/usercrud/changepass");
        return "user/base";
    }
    @PostMapping("/updatepassword")
    public String updateUser(@ModelAttribute(ATTRIBUTE_USER) ChangePasswordRequest passwordChangeRequest) {
        userService.changePassword(passwordChangeRequest.getId(),passwordChangeRequest);
        return "redirect:/userupdateanddetails/"+passwordChangeRequest.getId();
    }

    @GetMapping("/getorders/{userId}")
    public String getOrdersByUserId(@PathVariable UUID userId, Model model)
    {
        var orders = userService.getAllOrdersByUser(userId);
        model.addAttribute("pageTitle", "Orders");
        model.addAttribute("order_list", orders);
        model.addAttribute("content", "user/order/index");
        return "user/base";
    }

}
