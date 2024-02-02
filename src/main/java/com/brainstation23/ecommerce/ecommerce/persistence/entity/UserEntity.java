package com.brainstation23.ecommerce.ecommerce.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "user_addresses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    private List<AddressEntity> address = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "user_cart",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "cart_item_id"))
    private List<CartItemEntity> cartItems = new ArrayList<>();
}
