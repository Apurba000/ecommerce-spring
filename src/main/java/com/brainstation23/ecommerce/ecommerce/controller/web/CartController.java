package com.brainstation23.ecommerce.ecommerce.controller.web;


import com.brainstation23.ecommerce.ecommerce.exception.custom.NotFoundException;
import com.brainstation23.ecommerce.ecommerce.mapper.CartItemMapper;
import com.brainstation23.ecommerce.ecommerce.model.domain.User;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.UserEntity;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.CartItemService;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user/cart")
public class CartController {

    private static final String USER_BASE = "user/base";
    private static final String ATTRIBUTE_PAGE_TITLE = "pageTitle";
    private static final String ATTRIBUTE_CONTENT = "content";

    private final CartItemService cartItemService;

    private final CartItemMapper cartItemMapper;

    private final UserService userService;


    @GetMapping
    public String cartHome(@RequestHeader(value = HttpHeaders.REFERER, required = false) final String referrer, Model model) {
        String redirectUrl = (referrer == null) ? "/landingpage" : referrer;
        model.addAttribute("previousUrl", redirectUrl);
        model.addAttribute(ATTRIBUTE_PAGE_TITLE, "Cart");
        model.addAttribute(ATTRIBUTE_CONTENT, "user/usercrud/cart");

        UserEntity userSession = userService.getSessionUser();
        if (userSession == null) throw new NotFoundException("Please Log in First");

        User user = userService.getOne(userSession.getId());
        var cartItemList = user.getCartItems();
        var cartItemResponse = cartItemList.stream().map(cartItemMapper::domainToResponse).toList();
        model.addAttribute("cart_items", cartItemResponse);
        return USER_BASE;
    }
    private Pageable getDefaultProductPage() {
        return PageRequest.of(0, 20, Sort.by(
                Sort.Order.asc("date"),
                Sort.Order.desc("id")));
    }


}
