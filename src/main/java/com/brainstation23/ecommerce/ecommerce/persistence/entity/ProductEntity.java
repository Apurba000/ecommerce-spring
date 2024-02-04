package com.brainstation23.ecommerce.ecommerce.persistence.entity;

import com.brainstation23.ecommerce.ecommerce.constant.ColumnConstant;
import com.brainstation23.ecommerce.ecommerce.constant.EntityConstant;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.JdbcTypeCode;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Table(name = EntityConstant.PRODUCTS)
public class ProductEntity {
    @Id @GeneratedValue
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

    @NonNull
    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Column(name = ColumnConstant.UNIT_PRICE)
    private BigDecimal unitPrice;

    @NonNull
    @NotBlank
    @Size(max = 200)
    private String description;

    @NonNull
    @NotBlank
    @Size(max = 255)
    @Column(name = ColumnConstant.IMAGE_URL)
    private String imageUrl;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = ColumnConstant.PRODUCT_CATEGORIES,
            joinColumns = @JoinColumn(name = ColumnConstant.PRODUCT_ID),
            inverseJoinColumns = @JoinColumn(name = ColumnConstant.CATEGORY_ID))
    private List<CategoryEntity> categories = new ArrayList<>();
}
