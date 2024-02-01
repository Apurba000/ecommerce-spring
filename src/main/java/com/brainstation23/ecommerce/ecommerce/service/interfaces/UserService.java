package com.brainstation23.ecommerce.ecommerce.service.interfaces;

import com.brainstation23.ecommerce.ecommerce.model.domain.User;
import com.brainstation23.ecommerce.ecommerce.model.dto.user.UserCreateRequest;
import com.brainstation23.ecommerce.ecommerce.model.dto.user.UserUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<User> getAll(Pageable pageable);
    User getOne(Long id);
    Long createOne(UserCreateRequest createRequest);
    void updateOne(Long id, UserUpdateRequest updateRequest);
    void deleteOne(Long id);
}
