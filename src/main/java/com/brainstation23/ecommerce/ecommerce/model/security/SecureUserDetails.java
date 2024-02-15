package com.brainstation23.ecommerce.ecommerce.model.security;

import com.brainstation23.ecommerce.ecommerce.persistence.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SecureUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String phone;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public static SecureUserDetails build(UserEntity userEntity) {
        List<GrantedAuthority> authorities = userEntity.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new SecureUserDetails(
                userEntity.getId(),
                userEntity.getFirstname(),
                userEntity.getLastname(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getPhone(),
                userEntity.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecureUserDetails user = (SecureUserDetails) o;
        return Objects.equals(id, user.id);
    }

}
