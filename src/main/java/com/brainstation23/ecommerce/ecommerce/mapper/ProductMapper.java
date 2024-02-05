package com.brainstation23.ecommerce.ecommerce.mapper;

import com.brainstation23.ecommerce.ecommerce.model.domain.Product;
import com.brainstation23.ecommerce.ecommerce.model.dto.product.ProductCreateUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.product.ProductResponse;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product entityToDomain(ProductEntity entity);
    ProductResponse domainToResponse(Product product);
    ProductEntity requestToEntity(ProductCreateUpdateRequest productCreateRequest);
    Product requestToDomain(ProductCreateUpdateRequest productCreateRequest);
    ProductEntity domainToEntity(Product product);
    ProductCreateUpdateRequest domainToRequest(Product product);

}
