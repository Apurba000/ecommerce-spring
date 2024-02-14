package com.brainstation23.ecommerce.ecommerce.controller.web;

import com.brainstation23.ecommerce.ecommerce.mapper.CategoryMapper;
import com.brainstation23.ecommerce.ecommerce.mapper.ProductMapper;
import com.brainstation23.ecommerce.ecommerce.model.domain.Product;
import com.brainstation23.ecommerce.ecommerce.model.dto.product.ProductCreateUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.service.impl.ProductService;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

//@PreAuthorize("hasRole('ADMIN')")
@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    private static final String ATTRIBUTE_PRODUCT = "product";
    private static final String REDIRECT_ADMIN = "redirect:/admin";

    private static final String ADMIN_BASE = "admin/base";

    private static final String ATTRIBUTE_PAGE_TITLE = "pageTitle";
    private static final String ATTRIBUTE_CONTENT = "content";
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public String adminBoard(Model model) {
        Page<Product> pageProductDomains = productService.getAll(getDefaultProductPage());
        model.addAttribute("product_list", pageProductDomains.map(productMapper::domainToResponse));
        model.addAttribute(ATTRIBUTE_PAGE_TITLE, "Admin Home");
        model.addAttribute(ATTRIBUTE_CONTENT, "admin/admin_home");
        return ADMIN_BASE;
    }

    private Pageable getDefaultProductPage() {
        return PageRequest.of(0, 20, Sort.by(
                Sort.Order.asc("name"),
                Sort.Order.desc("id")));
    }

    @GetMapping("/addnewproduct")
    public String addProduct(Model model) {
        ProductCreateUpdateRequest productCreateRequest = new ProductCreateUpdateRequest();
        var categories = categoryService.getAll();
        model.addAttribute("categories", categories.stream()
                .map(categoryMapper::domainToResponse)
                .collect(Collectors.toList()));
        model.addAttribute(ATTRIBUTE_PRODUCT, productCreateRequest);
        model.addAttribute(ATTRIBUTE_PAGE_TITLE, "Add Product");
        model.addAttribute(ATTRIBUTE_CONTENT, "admin/add_update_product");
        model.addAttribute("action", "/admin/save");
        return ADMIN_BASE;
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute(ATTRIBUTE_PRODUCT) ProductCreateUpdateRequest productCreateRequest) {
        productService.createOne(productCreateRequest);
        return REDIRECT_ADMIN;
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormUpdate(@PathVariable(value = "id") UUID id, Model model) {
        Product product = productService.getOne(id);
        ProductCreateUpdateRequest request = productMapper.domainToRequest(product);
        var categories = categoryService.getAll();
        model.addAttribute("categories", categories.stream()
                .map(categoryMapper::domainToResponse)
                .collect(Collectors.toList()));

        model.addAttribute(ATTRIBUTE_PRODUCT, request);
        model.addAttribute(ATTRIBUTE_PAGE_TITLE, "Update Product");
        model.addAttribute(ATTRIBUTE_CONTENT, "admin/add_update_product");
        model.addAttribute("action", "/admin/update");
        return ADMIN_BASE;
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute(ATTRIBUTE_PRODUCT) ProductCreateUpdateRequest productUpdateRequest) {
        productService.updateOne(productUpdateRequest);
        return REDIRECT_ADMIN;
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteEmployee(@PathVariable(value = "id") UUID id) {
        productService.deleteOne(id);
        return REDIRECT_ADMIN;
    }


}
