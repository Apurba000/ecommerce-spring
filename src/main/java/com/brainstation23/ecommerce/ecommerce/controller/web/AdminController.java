package com.brainstation23.ecommerce.ecommerce.controller.web;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    private static final String ATTRIBUTE_PRODUCT = "product";
    private static final String REDIRECT_ADMIN = "redirect:/admin";
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;

    @GetMapping
    public String adminBoard(Model model) {
        Page<Product> pageProductDomains = productService.getAll(getDefaultProductPage());
        model.addAttribute("product_list", pageProductDomains.map(productMapper::domainToResponse));
        return "admin_home";
    }

    private Pageable getDefaultProductPage() {
        return PageRequest.of(0, 20, Sort.by(
                Sort.Order.asc("name"),
                Sort.Order.desc("id")));
    }

    @GetMapping("/addnewproduct")
    public String addProduct(Model model) {
        ProductCreateUpdateRequest productCreateRequest = new ProductCreateUpdateRequest();
        var categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);
        model.addAttribute(ATTRIBUTE_PRODUCT, productCreateRequest);
        return "new_product";
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
        request.buildCateGoryStr();
        var categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);
        model.addAttribute(ATTRIBUTE_PRODUCT, request);
        return "update_product";
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
