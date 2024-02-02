package com.brainstation23.ecommerce.ecommerce.controller.web;

import com.brainstation23.ecommerce.ecommerce.model.domain.Product;
import com.brainstation23.ecommerce.ecommerce.service.impl.ProductService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;

    @GetMapping
    public String adminBoard(Model model) {
        Page<Product> pageProduct = productService.getAll(getDefaultPage());
        log.info("Product List Total = " + pageProduct.getTotalElements());
        pageProduct.forEach(it -> log.info(it.getName()));

        model.addAttribute("product_list", pageProduct);
        return "admin_home";
    }

    private Pageable getDefaultPage(){
        return PageRequest.of(0, 20, Sort.by(
                Sort.Order.asc("name"),
                Sort.Order.desc("id")));
    }

}
