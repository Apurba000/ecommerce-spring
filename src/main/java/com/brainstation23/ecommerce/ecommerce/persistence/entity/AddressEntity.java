package com.brainstation23.ecommerce.ecommerce.persistence.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.JdbcTypeCode;

import java.math.BigInteger;
import java.sql.Types;
import java.util.UUID;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "addresses")
public class AddressEntity {
    @Id @GeneratedValue
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

    @NotBlank
    @Size(max = 255)
    private String details;

    @NotBlank
    @Size(max = 20)
    @Column(name = "zip_code")
    private String zipCode;
}
