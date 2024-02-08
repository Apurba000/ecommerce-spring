package com.brainstation23.ecommerce.ecommerce.controller.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/orders")
public class OrderAdminController {
    private static final String ORDERS_BASE = "admin/orders_admin";
    private static final String ADMIN_BASE = "admin/base";
    private static final String ATTRIBUTE_PAGE_TITLE = "pageTitle";
    private static final String ATTRIBUTE_CONTENT = "content";

    @GetMapping
    public String adminBoard(Model model) {
//        Page<Product> pageProductDomains = productService.getAll(getDefaultProductPage());
//        model.addAttribute("product_list", pageProductDomains.map(productMapper::domainToResponse));
//        model.addAttribute(ATTRIBUTE_PAGE_TITLE, "Admin Home");
//        model.addAttribute(ATTRIBUTE_CONTENT, "admin/admin_home");

        model.addAttribute(ATTRIBUTE_PAGE_TITLE, "Orders");
        model.addAttribute(ATTRIBUTE_CONTENT, ORDERS_BASE);
        return ADMIN_BASE;
    }

}
