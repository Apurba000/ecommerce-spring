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

import static com.brainstation23.ecommerce.ecommerce.constant.EntityConstant.*;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = USER_TABLE,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotBlank
    @Size(max = 50)
    private String firstname;

    @NonNull
    @NotBlank
    @Size(max = 50)
    private String lastname;

    @NonNull
    @NotBlank
    @Size(max = 50)
    private String username;

    @NonNull
    @NotBlank
    @Size(max = 255)
    @Email
    private String email;

    @NonNull
    @NotBlank
    @Size(max = 20)
    private String phone;

    @NonNull
    @NotBlank
    @Size(max = 255)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = USER_ROLES_TABLE,
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = USER_ADDRESS_TABLE,
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    private List<AddressEntity> address = new ArrayList<>();
}
