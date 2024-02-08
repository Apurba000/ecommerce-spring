package com.brainstation23.ecommerce.ecommerce.model.domain;

import com.brainstation23.ecommerce.ecommerce.constant.ColumnConstant;
import com.brainstation23.ecommerce.ecommerce.constant.EntityConstant;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.AddressEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.CartItemEntity;
import com.brainstation23.ecommerce.ecommerce.persistence.entity.RoleEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private UUID id;

    private String firstname;

    private String lastname;

    private String username;

    private String email;

    private String phone;

    private String password;

    private Set<Role> roles;

    private List<Address> address;

    private List<CartItem> cartItems;
}
