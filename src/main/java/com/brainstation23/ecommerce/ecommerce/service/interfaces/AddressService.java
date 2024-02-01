package com.brainstation23.ecommerce.ecommerce.service.interfaces;

import com.brainstation23.ecommerce.ecommerce.model.domain.Address;
import com.brainstation23.ecommerce.ecommerce.model.dto.Address.AddressCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.Address.AddressUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AddressService {
    Page<Address> getAll(Pageable pageable);
    Address getOne(Long id);
    Long createOne(AddressCreateRequest createRequest);
    void updateOne(Long id, AddressUpdateRequest updateRequest);
    void deleteOne(Long id);
}
