package com.brainstation23.ecommerce.ecommerce.controller.web;

import com.brainstation23.ecommerce.ecommerce.mapper.OrderMapper;
import com.brainstation23.ecommerce.ecommerce.model.dto.order.OrderAdminResponse;
import com.brainstation23.ecommerce.ecommerce.model.dto.order.OrderUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.OrderService;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.UserStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@PreAuthorize("hasRole('ADMIN')")
@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/orders")
public class OrderAdminController {
    private static final String ORDERS_BASE = "admin/order/index";
    private static final String ADMIN_BASE = "admin/base";
    private static final String ATTRIBUTE_PAGE_TITLE = "pageTitle";
    private static final String REDIRECT_TO_ORDER = "redirect:/admin/orders";
    private static final String ATTRIBUTE_CONTENT = "content";
    private final OrderService orderService;
    private final UserStatus userStatus;
    @GetMapping
    public String adminBoard(Model model) {
        userStatus.loginStatus(model);
        var orders = orderService.getAll(getDefaultOrderPage());

        model.addAttribute("order_list", orders);
        model.addAttribute(ATTRIBUTE_PAGE_TITLE, "Orders");
        model.addAttribute(ATTRIBUTE_CONTENT, ORDERS_BASE);
        return ADMIN_BASE;
    }
    @PostMapping("/changeOrderStatus")
    public String orderStatusChange(@ModelAttribute("orderUpdateRequest") OrderUpdateRequest orderUpdateRequest)
    {
        orderService.updateOrderStatus(orderUpdateRequest.getId(),orderUpdateRequest.getStatus());
        return REDIRECT_TO_ORDER;
    }
    private Pageable getDefaultOrderPage() {
        return PageRequest.of(0, 20, Sort.by(
                Sort.Order.asc("orderDate")));
    }

}
