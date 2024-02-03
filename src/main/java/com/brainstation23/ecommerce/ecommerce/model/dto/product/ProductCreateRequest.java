package com.brainstation23.ecommerce.ecommerce.model.dto.product;

import com.brainstation23.ecommerce.ecommerce.persistence.entity.CategoryEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
public class ProductCreateRequest {
    private String name;
    private BigDecimal unitPrice;
    private String description;
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
