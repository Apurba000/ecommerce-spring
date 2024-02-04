package com.brainstation23.ecommerce.ecommerce.model.dto.product;

import com.brainstation23.ecommerce.ecommerce.persistence.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private String name;
    private BigDecimal unitPrice;
    private String description;
    private String imageUrl;
    private List<CategoryEntity> categories = new ArrayList<>();
}
