package com.brainstation23.ecommerce.ecommerce.controller.web;

import com.brainstation23.ecommerce.ecommerce.exception.custom.NotFoundException;
import com.brainstation23.ecommerce.ecommerce.mapper.CartItemMapper;
import com.brainstation23.ecommerce.ecommerce.model.domain.User;
import com.brainstation23.ecommerce.ecommerce.model.dto.cartItem.Cart;
import com.brainstation23.ecommerce.ecommerce.model.dto.cartItem.CartItemCreateUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.UserEntity;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.CartItemService;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user/cart")
public class CartController {

    private static final String USER_BASE = "user/base";
    private static final String ATTRIBUTE_PAGE_TITLE = "pageTitle";
    private static final String ATTRIBUTE_CONTENT = "content";

    private static final String CART_ATTRIBUTE = "cart";

    private final CartItemMapper cartItemMapper;

    private final UserService userService;

    private final CartItemService cartItemService;


    @GetMapping
    public String allCartItems(@RequestHeader(value = HttpHeaders.REFERER, required = false) final String referrer, Model model) {
        String redirectUrl = "/landingpage";
        model.addAttribute("previousUrl", redirectUrl);
        model.addAttribute(ATTRIBUTE_PAGE_TITLE, "Cart");
        model.addAttribute(ATTRIBUTE_CONTENT, "user/usercrud/cart");

        UserEntity userSession = userService.getSessionUser();
        if (userSession == null) throw new NotFoundException("Please Log in First");

        User user = userService.getOne(userSession.getId());
        var cartItemList = user.getCartItems();
        var cartItemResponseList = cartItemList.stream().map(cartItemMapper::domainToResponse).toList();

        Cart cart = new Cart().setCartItemList(cartItemResponseList);
        model.addAttribute(CART_ATTRIBUTE, cart);
        return USER_BASE;
    }

    @PostMapping("/update")
    public String updateCartItem(@ModelAttribute CartItemCreateUpdateRequest updatedItem) {
        updatedItem.setDate(new Timestamp(System.currentTimeMillis()));
        cartItemService.updateOne(updatedItem);
        return "redirect:/user/cart";
    }

    @GetMapping("/delete/{id}")
    public String deleteCartItem(@PathVariable(value = "id") UUID id) {
        cartItemService.deleteOne(id);
        return "redirect:/user/cart";
    }

}
