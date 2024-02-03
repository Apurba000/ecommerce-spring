package com.brainstation23.ecommerce.ecommerce.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.JdbcTypeCode;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.*;

import static org.hibernate.annotations.CascadeType.*;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Table(name = "products")
public class ProductEntity {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

    @NonNull
    @NotBlank
    @Size(max = 50)
    private String name;

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
    @Cascade({ PERSIST, REFRESH, MERGE})
    @JoinTable(  name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<CategoryEntity> categories = new HashSet<>();
}
