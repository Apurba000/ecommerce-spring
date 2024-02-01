package com.brainstation23.ecommerce.ecommerce.mapper;

import com.brainstation23.ecommerce.ecommerce.model.domain.Address;
import com.brainstation23.ecommerce.ecommerce.model.dto.Address.AddressResponse;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.AddressEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address entityToDomain(AddressEntity entity);
    AddressResponse domainToResponse(Address address);
}
