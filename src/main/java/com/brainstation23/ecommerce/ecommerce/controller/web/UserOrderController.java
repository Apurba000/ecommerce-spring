package com.brainstation23.ecommerce.ecommerce.controller.web;

import com.brainstation23.ecommerce.ecommerce.constant.OtherConstants;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.OrderService;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.UserService;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.UserStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@PreAuthorize("hasRole('USER')")
@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user/getOrder")
public class UserOrderController {
    private final OrderService orderService;
    private final UserStatus userStatus;
    private final UserService userService;
    @GetMapping("{orderId}")
    public String getOrderByOrderId(@PathVariable UUID orderId, Model model)
    {
        var userEntity = userStatus.getCurrentUser();
        if (userEntity == null)
        {
            return OtherConstants.signIn;
        };
        userStatus.loginStatus(model);
        var order = orderService.getOne(orderId);
        model.addAttribute("pageTitle", "Order Details");
        model.addAttribute("order", order);
        model.addAttribute("content", "user/order/orderdetails");
        return "user/base";
    }
}
