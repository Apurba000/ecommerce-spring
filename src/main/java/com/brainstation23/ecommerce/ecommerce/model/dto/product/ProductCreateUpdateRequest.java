package com.brainstation23.ecommerce.ecommerce.model.dto.product;

import com.brainstation23.ecommerce.ecommerce.persistence.entity.CategoryEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@ToString
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateUpdateRequest {
    private UUID id;
    @NotEmpty
    @Size(max = 50)
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
