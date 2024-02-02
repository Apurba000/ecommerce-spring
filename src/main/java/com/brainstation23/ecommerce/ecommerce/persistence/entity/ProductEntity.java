package com.brainstation23.ecommerce.ecommerce.persistence.entity;

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
@Table(name = "products")
public class ProductEntity {
    @Id @GeneratedValue
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

    @NonNull
    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @NonNull
    @NotBlank
    @Size(max = 200)
    private String description;

    @NonNull
    @NotBlank
    @Size(max = 255)
    @Column(name = "image_url")
    private String imageUrl;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<CategoryEntity> categories = new ArrayList<>();
}
