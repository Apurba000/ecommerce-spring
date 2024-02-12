package com.brainstation23.ecommerce.ecommerce.controller.web;

import com.brainstation23.ecommerce.ecommerce.model.dto.user.UserCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.user.UserSignInRequest;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.UserService;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.UserStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class CreateUserController {
    private static final String ATTRIBUTE_PAGE_TITLE = "pageTitle";
    private static final String ATTRIBUTE_USER = "user";
    private static final String ATTRIBUTE_CONTENT = "content";
    private final UserStatus userStatus;
    private static final String USER_BASE = "user/base";
    private final UserService userService;
    //private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/create")
    public String createUser(Model model) {
        userStatus.loginStatus(model);
        UserCreateRequest userCreateRequest = new UserCreateRequest();
        model.addAttribute(ATTRIBUTE_PAGE_TITLE, "New User");
        model.addAttribute(ATTRIBUTE_CONTENT, "user/usercrud/create");
        model.addAttribute("action", "/admin/save");
        return USER_BASE;
    }
    @PostMapping("/save")
    public String saveUser(@ModelAttribute(ATTRIBUTE_USER) UserCreateRequest userCreateRequest) {
        //String encodedPassword = passwordEncoder.encode(userCreateRequest.getPassword());
        // Set the encrypted password back to the userCreateRequest
        //userCreateRequest.setPassword(encodedPassword);
        userService.createOne(userCreateRequest);
        return "redirect:/admin";
    }

    @GetMapping("/sign-in-form")
    public String signInFrom(@RequestHeader(value = HttpHeaders.REFERER, required = false) final String referrer, Model model) {
        userStatus.loginStatus(model);
        String redirectUrl = (referrer == null) ? "/landingpage" : referrer;
        model.addAttribute("previousUrl", redirectUrl);
        model.addAttribute(ATTRIBUTE_PAGE_TITLE, "Sign In");
        model.addAttribute(ATTRIBUTE_CONTENT, "user/usercrud/signin");
        return USER_BASE;
    }

    @PostMapping("/sign-in")
    public String signIn(@ModelAttribute(ATTRIBUTE_USER) UserSignInRequest signInRequest
            , @ModelAttribute("previousUrl") String redirectUrl) {
        userService.signIn(signInRequest);
        return "redirect:" + redirectUrl;
    }
}
