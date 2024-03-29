package com.brainstation23.ecommerce.ecommerce.controller.web;

import com.brainstation23.ecommerce.ecommerce.model.dto.user.UserCreateRequest;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.UserService;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.UserStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class AuthWebController {
    private static final String ATTRIBUTE_PAGE_TITLE = "pageTitle";
    private static final String ATTRIBUTE_USER = "user";
    private static final String ATTRIBUTE_CONTENT = "content";
    private final UserStatus userStatus;
    private static final String USER_BASE = "user/base";
    private final UserService userService;

    @GetMapping("/create")
    public String createUser(Model model) {
        userStatus.loginStatus(model);
        UserCreateRequest userCreateRequest = new UserCreateRequest();
        model.addAttribute(ATTRIBUTE_PAGE_TITLE, "New User");
        model.addAttribute(ATTRIBUTE_CONTENT, "user/usercrud/create");
        model.addAttribute("action", "/user/save");
        return USER_BASE;
    }
    @PostMapping("/save")
    public String saveUser(@ModelAttribute(ATTRIBUTE_USER) UserCreateRequest userCreateRequest) {
        userService.createOne(userCreateRequest);
        return "redirect:sign-in-form";
    }

    @GetMapping("/sign-in-form")
    public String signInFrom(Model model) {
        userStatus.loginStatus(model);
        model.addAttribute(ATTRIBUTE_PAGE_TITLE, "Sign In");
        model.addAttribute(ATTRIBUTE_CONTENT, "user/usercrud/signin");
        return USER_BASE;
    }

}
