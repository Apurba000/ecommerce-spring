package com.brainstation23.ecommerce.ecommerce.model.dto.product;

import com.brainstation23.ecommerce.ecommerce.model.domain.Category;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;



@Getter
@Setter
@Accessors(chain = true)
public class ProductResponse {
    private UUID id;
    private String name;
    private BigDecimal unitPrice;
    private String description;
    private String imageUrl;
    private Set<Category> categories;
    public String categoriesStr(){
        return (this.categories == null || categories.isEmpty()) ?
                "No Category Found" : categories
                .iterator().next()
                .getCategoryName();
    }
}
