package com.brainstation23.ecommerce.ecommerce.model.dto.product;

import com.brainstation23.ecommerce.ecommerce.persistence.entity.CategoryEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@ToString
@Getter
@Setter
public class ProductCreateUpdateRequest {
    private UUID id;
    private String name;
    private BigDecimal unitPrice;
    private String description;
    private String imageUrl;
    private String categoryStr;
    private List<CategoryEntity> categories;
    public void buildCateGoryStr(){
        this.categoryStr = (categories == null || categories.isEmpty()) ?
                "No Category Found" : categories.get(0).getCategoryName();
    }
}
