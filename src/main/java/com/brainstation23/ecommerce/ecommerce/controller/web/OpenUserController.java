package com.brainstation23.ecommerce.ecommerce.controller.web;

import com.brainstation23.ecommerce.ecommerce.model.dto.user.UserCreateRequest;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.UserService;
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
@RequestMapping("/user")
public class OpenUserController {
    private static final String ATTRIBUTE_PAGE_TITLE = "pageTitle";
    private static final String ATTRIBUTE_USER = "user";
    private static final String ATTRIBUTE_CONTENT = "content";
    private final UserService userService;
    //private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/create")
    public String createUser(Model model) {
        UserCreateRequest userCreateRequest = new UserCreateRequest();
        model.addAttribute(ATTRIBUTE_PAGE_TITLE, "New User");
        model.addAttribute(ATTRIBUTE_CONTENT, "user/usercrud/create");
        model.addAttribute("action", "/admin/save");
        return "user/base";
    }
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute(ATTRIBUTE_USER) UserCreateRequest userCreateRequest) {
        //String encodedPassword = passwordEncoder.encode(userCreateRequest.getPassword());
        // Set the encrypted password back to the userCreateRequest
        //userCreateRequest.setPassword(encodedPassword);
        userService.createOne(userCreateRequest);
        return "redirect:/admin";
    }
}
