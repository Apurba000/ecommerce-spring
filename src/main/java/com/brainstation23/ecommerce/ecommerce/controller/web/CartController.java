package com.brainstation23.ecommerce.ecommerce.controller.web;

import com.brainstation23.ecommerce.ecommerce.constant.OtherConstants;
import com.brainstation23.ecommerce.ecommerce.exception.custom.NotFoundException;
import com.brainstation23.ecommerce.ecommerce.mapper.CartItemMapper;
import com.brainstation23.ecommerce.ecommerce.model.domain.Address;
import com.brainstation23.ecommerce.ecommerce.model.domain.CartItem;
import com.brainstation23.ecommerce.ecommerce.model.domain.OrderItem;
import com.brainstation23.ecommerce.ecommerce.model.domain.User;
import com.brainstation23.ecommerce.ecommerce.model.dto.cartItem.Cart;
import com.brainstation23.ecommerce.ecommerce.model.dto.cartItem.CartItemCreateUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.order.OrderCreateRequest;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.UserEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.repository.UserRepository;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.brainstation23.ecommerce.ecommerce.controller.web.UserUpdateAndDetailsController.USER_DETAILS;

@PreAuthorize("hasRole('CUSTOMER')")
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

    private final OrderService orderService;

    private final UserRepository userRepository;
    private final AddressService addressService;
    private final UserStatus userStatus;

    @GetMapping
    public String allCartItems(Model model) {
        userStatus.loginStatus(model);
        String redirectUrl = "/landingpage";
        model.addAttribute("previousUrl", redirectUrl);
        model.addAttribute(ATTRIBUTE_PAGE_TITLE, "Cart");
        model.addAttribute(ATTRIBUTE_CONTENT, "user/usercrud/cart");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUserName(authentication.getName());
        if (user == null)
        {
            return OtherConstants.signIn;
        };
        var cartItemList = user.getCartItems();
        var cartItemResponseList = cartItemList.stream().map(cartItemMapper::domainToResponse).toList();

        Cart cart = new Cart().setCartItemList(cartItemResponseList);
        model.addAttribute(CART_ATTRIBUTE, cart);
        model.addAttribute("total", calculateTotal(cartItemList));
        model.addAttribute("addressList", user.getAddress());
        model.addAttribute("deliveryAddress", new Address());
        return USER_BASE;
    }

    private BigDecimal calculateTotal(List<CartItem> cartItemList) {
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem item : cartItemList) {
            BigDecimal unitPrice = item.getProduct().getUnitPrice();
            int quantity = item.getQuantity();
            total = total.add(unitPrice.multiply(BigDecimal.valueOf(quantity)));
        }
        return total;
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

    @PostMapping("/place_order")
    public String placeOrder(@ModelAttribute Address deliveryAddress) {
        if (deliveryAddress.getId() == null) throw new NotFoundException("Please Select Delivery Address First");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByUserName(authentication.getName());
        if (user == null)
        {
            return OtherConstants.signIn;
        };

        if (user.getCartItems().isEmpty()) throw new NotFoundException("Cart is Empty!");

        placeOrder(deliveryAddress, user);

        userService.clearCart(user.getId());

        return "redirect:" + USER_DETAILS + "/getorders";
    }

    private void placeOrder(Address deliveryAddress, User user){
        var cartItemList = user.getCartItems();
        Set<OrderItem> orderItems = getOrderItems(cartItemList);
        deliveryAddress = addressService.getOne(deliveryAddress.getId());

        OrderCreateRequest orderCreateRequest = new OrderCreateRequest();
        orderCreateRequest
                .setUser(user)
                .setItems(orderItems)
                .setDeliveryAddress(deliveryAddress)
                .setTotalAmount(calculateTotal(cartItemList));

        orderService.createOne(orderCreateRequest);
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@ModelAttribute("cartItem") CartItemCreateUpdateRequest cartItemCreateRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = userService.getUserByUserName(authentication.getName());
        if (user == null)
        {
            return OtherConstants.signIn;
        };
        var userEntity = userRepository.findById(user.getId()).orElseThrow();
        cartItemCreateRequest.setUser(userEntity);
        cartItemService.createOne(cartItemCreateRequest);
        return "redirect:/user/cart";
    }
    private Set<OrderItem> getOrderItems(List<CartItem> cartItems) {
        Set<OrderItem> orderItems = new HashSet<>();
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();

            orderItem.setProduct(cartItem.getProduct())
                    .setQuantity(cartItem.getQuantity())
                    .setUnitPrice(cartItem.getProduct().getUnitPrice());

            orderItems.add(orderItem);
        }
        return orderItems;
    }
}
