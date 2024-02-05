package com.brainstation23.ecommerce.ecommerce.controller.web;

import com.brainstation23.ecommerce.ecommerce.mapper.ProductMapper;
import com.brainstation23.ecommerce.ecommerce.model.domain.Product;
import com.brainstation23.ecommerce.ecommerce.model.dto.product.ProductCreateRequest;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    private static final String ATTRIBUTE_PRODUCT = "product";

    private final ProductService productService;
    private final ProductMapper mapper;

    @GetMapping
    public String adminBoard(Model model) {
        Page<Product> pageProductDomains = productService.getAll(getDefaultProductPage());

//        log.info("ProductDomains List Total = " + pageProductDomains.getTotalElements());
//        pageProductDomains.forEach(it -> log.info(it.getName()));

        model.addAttribute("product_list", pageProductDomains.map(mapper::domainToResponse));
        return "admin_home";
    }
    private Pageable getDefaultProductPage(){
        return PageRequest.of(0, 20, Sort.by(
                Sort.Order.asc("name"),
                Sort.Order.desc("id")));
    }


    @GetMapping("/addnewproduct")
    public String addProduct(Model model) {
        ProductCreateRequest productCreateRequest = new ProductCreateRequest();
        model.addAttribute(ATTRIBUTE_PRODUCT, productCreateRequest);
        return "new_product";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute(ATTRIBUTE_PRODUCT) ProductCreateRequest productCreateRequest) {
        productCreateRequest.buildCategoryEntities();
        productService.createOne(productCreateRequest);
        return "redirect:/admin";
    }

}
