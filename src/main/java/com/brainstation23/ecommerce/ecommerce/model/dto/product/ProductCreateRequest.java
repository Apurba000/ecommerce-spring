package com.brainstation23.ecommerce.ecommerce.model.dto.product;

import com.brainstation23.ecommerce.ecommerce.persistence.entity.CategoryEntity;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
public class ProductCreateRequest {
    @NotEmpty
    @Size(max = 50)
    private String name;

    private BigDecimal unitPrice;

    @NotEmpty
    private String description;

    @NotEmpty
    private String imageUrl;

    private String categoryStr;
    private List<CategoryEntity> categories;
    public void buildCategoryEntities(){
        if (!StringUtils.isEmpty(categoryStr)){
            this.categories = new ArrayList<>();
            categories.add(new CategoryEntity().setCategoryName(categoryStr));
        }
    }
}
