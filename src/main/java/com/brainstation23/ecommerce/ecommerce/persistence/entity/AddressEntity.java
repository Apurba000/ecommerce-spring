package com.brainstation23.ecommerce.ecommerce.persistence.entity;


import com.brainstation23.ecommerce.ecommerce.constant.ColumnConstant;
import com.brainstation23.ecommerce.ecommerce.constant.EntityConstant;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = EntityConstant.ADDRESSES)
public class AddressEntity {
    @Id
    @GeneratedValue
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

    @NotBlank
    @Size(max = 255, message = "Address Details max size 255 characters")
    private String details;

    @NotBlank
    @Size(max = 20, message = "Address Zip code max 20 characters")
    @Column(name = ColumnConstant.ZIP_CODE)
    private String zipCode;

}
