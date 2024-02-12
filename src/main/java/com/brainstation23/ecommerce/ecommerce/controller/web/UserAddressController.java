package com.brainstation23.ecommerce.ecommerce.controller.web;


import com.brainstation23.ecommerce.ecommerce.exception.custom.NotFoundException;
import com.brainstation23.ecommerce.ecommerce.mapper.UserMapper;
import com.brainstation23.ecommerce.ecommerce.model.domain.Address;
import com.brainstation23.ecommerce.ecommerce.model.domain.User;
import com.brainstation23.ecommerce.ecommerce.model.dto.user.UserUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.UserEntity;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user/address")
public class UserAddressController {
    private static final String USER_BASE = "user/base";
    private static final String ATTRIBUTE_PAGE_TITLE = "pageTitle";
    private static final String ATTRIBUTE_CONTENT = "content";

    private final UserService userService;

    private final UserMapper userMapper;

    @GetMapping("/add")
    public String addressForm(@RequestHeader(value = HttpHeaders.REFERER, required = false) final String referrer, Model model) {
        String redirectUrl = (referrer == null) ? "/landingpage" : referrer;
        model.addAttribute("previousUrl", redirectUrl);
        model.addAttribute(ATTRIBUTE_PAGE_TITLE, "Add Address");
        model.addAttribute(ATTRIBUTE_CONTENT, "user/usercrud/address");
        return USER_BASE;
    }

    @PostMapping("/save")
    public String saveAddress(@ModelAttribute("previousUrl") String redirectUrl, @ModelAttribute Address address) {
        log.info("Address Entered : " + address.getDetails());
        UserEntity userEntity = userService.getSessionUser();
        if (userEntity == null) throw new NotFoundException("Please Log in first");

        User user = userService.getOne(userEntity.getId());
        List<Address> addresses = user.getAddress() == null ? new ArrayList<>() : user.getAddress();
        addresses.add(address);
        user.setAddress(addresses);

        userService.updateOne(user.getId(), getUpdateRequest(user));
        return "redirect:" + redirectUrl;
    }

    private UserUpdateRequest getUpdateRequest(User user){
        UserUpdateRequest request = new UserUpdateRequest();
        return request.setFirstname(user.getFirstname())
                .setLastname(user.getLastname())
                .setEmail(user.getEmail())
                .setUsername(user.getUsername())
                .setPhone(user.getPhone())
                .setAddress(user.getAddress());
    }
}
