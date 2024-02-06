package com.brainstation23.ecommerce.ecommerce.controller.web;

import com.brainstation23.ecommerce.ecommerce.mapper.ProductMapper;
import com.brainstation23.ecommerce.ecommerce.model.domain.Product;
import com.brainstation23.ecommerce.ecommerce.model.dto.cartItem.CartItemCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.category.CategoryCreateRequest;
import com.brainstation23.ecommerce.ecommerce.service.impl.ProductService;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.CartItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/landingpage")
public class LandingPageController {
    private static final String ATTRIBUTE_CART_ITEM = "cartItem";
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final CartItemService cartItemService;
    @GetMapping
    public String home(Model model)
    {
        Page<Product> products = productService.getAll(getDefaultProductPage());
        model.addAttribute("product_list", products.map(productMapper::domainToResponse));
        model.addAttribute("pageTitle", "Products");
        model.addAttribute("content", "user/landingpage/index");
        CartItemCreateRequest cartItemCreateRequest = new CartItemCreateRequest();
        model.addAttribute(ATTRIBUTE_CART_ITEM, cartItemCreateRequest);
        return "user/base";
    };

    @PostMapping("/add-to-cart")
    public String addToCart(@ModelAttribute("cartItem") CartItemCreateRequest cartItemCreateRequest, Model model) {
        cartItemService.createOne(cartItemCreateRequest);
        return "redirect:/products";
    }

    private Pageable getDefaultProductPage() {
        return PageRequest.of(0, 20, Sort.by(
                Sort.Order.asc("name"),
                Sort.Order.desc("id")));
    }
}
