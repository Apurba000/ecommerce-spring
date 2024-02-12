package com.brainstation23.ecommerce.ecommerce.controller.web;

import com.brainstation23.ecommerce.ecommerce.service.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user/getOrder")
public class UserOrderController {
    private final OrderService orderService;

    @GetMapping("{orderId}")
    public String getOrderByOrderId(@PathVariable UUID orderId, Model model)
    {
        var order = orderService.getOne(orderId);
        model.addAttribute("pageTitle", "Order Details");
        model.addAttribute("order", order);
        model.addAttribute("content", "user/order/orderdetails");
        return "user/base";
    }
}
