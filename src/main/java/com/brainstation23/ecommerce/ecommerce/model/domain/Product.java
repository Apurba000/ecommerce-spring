package com.brainstation23.ecommerce.ecommerce.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
public class Product {
    private UUID id;
    private String name;
    private BigDecimal unitPrice;
    private String description;
    private String imageUrl;
    private Set<Category> categories;
}
