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

@Service
@Slf4j
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    public static final String ADDRESS_NOT_FOUND = "Address Not Found";
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;


    @Override
    public Page<Address> getAll(Pageable pageable) {
        var entities = addressRepository.findAll(pageable);
        return entities.map(addressMapper::entityToDomain);
    }

    @Override
    public Address getOne(Long id) {
        var entity =  addressRepository.findById(id).orElseThrow(()->new NotFoundException(ADDRESS_NOT_FOUND));
        return addressMapper.entityToDomain(entity);
    }

    @Override
    public Long createOne(AddressCreateRequest createRequest) {
        var entity = new AddressEntity();
        //OtherCode
        var createdEntity = addressRepository.save(entity);
        return createdEntity.getId();
    }

    @Override
    public void updateOne(Long id, AddressUpdateRequest updateRequest) {
        var entity = addressRepository.findById(id).orElseThrow(()->new NotFoundException(ADDRESS_NOT_FOUND));
        //Do Some Code
        addressRepository.save(entity);
    }

    @Override
    public void deleteOne(Long id) {
        addressRepository.deleteById(id);
    }
}
