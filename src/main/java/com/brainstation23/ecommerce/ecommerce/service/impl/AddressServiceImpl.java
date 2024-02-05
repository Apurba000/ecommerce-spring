package com.brainstation23.ecommerce.ecommerce.service.impl;

import com.brainstation23.ecommerce.ecommerce.exception.custom.NotFoundException;
import com.brainstation23.ecommerce.ecommerce.mapper.AddressMapper;
import com.brainstation23.ecommerce.ecommerce.model.domain.Address;
import com.brainstation23.ecommerce.ecommerce.model.dto.Address.AddressCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.Address.AddressUpdateRequest;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.AddressEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.repository.AddressRepository;
import com.brainstation23.ecommerce.ecommerce.service.interfaces.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService{
    public static final String ADDRESS_NOT_FOUND = "Address Not Found";
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;


    @Override
    public Page<Address> getAll(Pageable pageable) {
        var entities = addressRepository.findAll(pageable);
        return entities.map(addressMapper::entityToDomain);
    }

    @Override
    public Address getOne(UUID id) {
        var entity =  addressRepository.findById(id).orElseThrow(()->new NotFoundException(ADDRESS_NOT_FOUND));
        return addressMapper.entityToDomain(entity);
    }

    @Override
    public UUID createOne(AddressCreateRequest createRequest) {
        var entity = new AddressEntity();
        entity.setDetails(createRequest.getDetails())
                .setZipCode(createRequest.getZipCode());
        var createdEntity = addressRepository.save(entity);
        return createdEntity.getId();
    }

    @Override
    public void updateOne(UUID id, AddressUpdateRequest updateRequest) {
        var entity = addressRepository.findById(id).orElseThrow(()->new NotFoundException(ADDRESS_NOT_FOUND));
        entity.setDetails(updateRequest.getDetails())
                        .setZipCode(updateRequest.getZipCode());
        addressRepository.save(entity);
    }

    @Override
    public void deleteOne(UUID id) {
        addressRepository.deleteById(id);
    }
}
