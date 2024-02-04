package com.brainstation23.ecommerce.ecommerce.service.interfaces;

import com.brainstation23.ecommerce.ecommerce.model.domain.Address;
import com.brainstation23.ecommerce.ecommerce.model.dto.Address.AddressCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.Address.AddressUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AddressService {
    Page<Address> getAll(Pageable pageable);
    Address getOne(UUID id);
    UUID createOne(AddressCreateRequest createRequest);
    void updateOne(UUID id, AddressUpdateRequest updateRequest);
    void deleteOne(UUID id);
}
