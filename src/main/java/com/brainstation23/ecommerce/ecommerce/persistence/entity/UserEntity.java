package com.brainstation23.ecommerce.ecommerce.persistence.entity;

import com.brainstation23.ecommerce.ecommerce.constant.ColumnConstant;
import com.brainstation23.ecommerce.ecommerce.constant.EntityConstant;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.*;

import static org.hibernate.annotations.CascadeType.*;
import static org.hibernate.annotations.CascadeType.MERGE;


@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = EntityConstant.USERS,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = ColumnConstant.USERNAME),
                @UniqueConstraint(columnNames = ColumnConstant.EMAIL)
        })
public class UserEntity {

    @Id @GeneratedValue
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

    @NotBlank
    @Size(max = 50)
    private String firstname;

    @NotBlank
    @Size(max = 50)
    private String lastname;

    @NotBlank
    @Size(max = 50)
    private String username;

    @NotBlank
    @Size(max = 255)
    @Email
    private String email;

    @NotBlank
    @Size(max = 20)
    private String phone;

    @NotBlank
    @Size(max = 255)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(  name = EntityConstant.USER_ROLES,
            joinColumns = @JoinColumn(name = ColumnConstant.USER_ID),
            inverseJoinColumns = @JoinColumn(name = ColumnConstant.ROLE_ID))
    private Set<RoleEntity> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @Cascade({ PERSIST, REFRESH, MERGE, DETACH})
    @JoinTable(  name = EntityConstant.USER_ADDRESSES,
            joinColumns = @JoinColumn(name = ColumnConstant.USER_ID),
            inverseJoinColumns = @JoinColumn(name = ColumnConstant.ADDRESS_ID))
    private List<AddressEntity> address = new ArrayList<>();

    @OneToMany(mappedBy="user", orphanRemoval = true)
    @Cascade(ALL)
    private List<CartItemEntity> cartItems = new ArrayList<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @Cascade(ALL)
    private List<OrderEntity> orders = new ArrayList<>();

}
