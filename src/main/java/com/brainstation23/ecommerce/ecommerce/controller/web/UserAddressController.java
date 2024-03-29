package com.brainstation23.ecommerce.ecommerce.controller.web;


import com.brainstation23.ecommerce.ecommerce.constant.OtherConstants;
import com.brainstation23.ecommerce.ecommerce.exception.custom.NotFoundException;
import com.brainstation23.ecommerce.ecommerce.mapper.UserMapper;
import com.brainstation23.ecommerce.ecommerce.model.domain.Address;
import com.brainstation23.ecommerce.ecommerce.model.domain.User;
import com.brainstation23.ecommerce.ecommerce.model.dto.user.UserUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.UserEntity;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.AddressService;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.UserService;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.UserStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@PreAuthorize("hasRole('CUSTOMER')")
@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user/addresses")
public class UserAddressController {
    private static final String USER_BASE = "user/base";
    private static final String ATTRIBUTE_PAGE_TITLE = "pageTitle";
    private static final String ATTRIBUTE_CONTENT = "content";
    private final UserStatus userStatus;

    private final UserService userService;
    private final UserMapper userMapper;
    private final AddressService addressService;
    @GetMapping
    public String getUserAddresses(Model model) {
        var user = userStatus.getCurrentUser();
        if (user.isEmpty())
        {
            return OtherConstants.signIn;
        };
        userStatus.loginStatus(model);
        var getUser = userService.getOne(user.get().getId());
        var addresses = getUser.getAddress();
        model.addAttribute(ATTRIBUTE_PAGE_TITLE, "UserAddresses");
        model.addAttribute("addresses", addresses); // Change to "addresses" instead of "user_addresses"
        model.addAttribute(ATTRIBUTE_CONTENT, "user/address/index");
        return USER_BASE;
    }


    @GetMapping("/addorupdate")
    public String addressForm(@RequestHeader(value = HttpHeaders.REFERER, required = false) final String referrer,
                              @RequestParam(name = "id", required = false) UUID addressId, Model model) {
        var user = userStatus.getCurrentUser();
        if (user.isEmpty())
        {
            return OtherConstants.signIn;
        };
        userStatus.loginStatus(model);
        String redirectUrl = (referrer == null) ? "/landingpage" : referrer;
        model.addAttribute("previousUrl", redirectUrl);
        model.addAttribute(ATTRIBUTE_PAGE_TITLE, "Add/Edit Address");

        if (addressId != null) {
            Address address = addressService.getOne(addressId);
            model.addAttribute("address", address);
        } else {
            model.addAttribute("address", new Address());
        }

        model.addAttribute(ATTRIBUTE_CONTENT, "user/address/address");
        return USER_BASE;
    }


    @PostMapping("/save")
    public String saveAddress(@ModelAttribute("previousUrl") String redirectUrl, @ModelAttribute Address address) {
        log.info("Address Entered : " + address.getDetails());
        var userEntity = userStatus.getCurrentUser();
        if (userEntity.isEmpty())
        {
            return OtherConstants.signIn;
        };

        User user = userService.getOne(userEntity.get().getId());
        List<Address> addresses = user.getAddress() == null ? new ArrayList<>() : user.getAddress();

        if (address.getId() == null) {
            addresses.add(address);
        } else {
            for (int i = 0; i < addresses.size(); i++) {
                if (addresses.get(i).getId().equals(address.getId())) {
                    addresses.set(i, address);
                    break;
                }
            }
        }

        user.setAddress(addresses);
        userService.updateOne(user.getId(), getUpdateRequest(user));
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/delete/{id}")
    public String deleteAddress(@PathVariable UUID id) {
        var userEntity = userStatus.getCurrentUser();
        if (userEntity.isEmpty())
        {
            return OtherConstants.signIn;
        };
        User user = userService.getOne(userEntity.get().getId());
        List<Address> addresses = user.getAddress();

        addresses.removeIf(address -> address.getId().equals(id));

        userService.updateOne(user.getId(), getUpdateRequest(user));

        return "redirect:/user/addresses";
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
