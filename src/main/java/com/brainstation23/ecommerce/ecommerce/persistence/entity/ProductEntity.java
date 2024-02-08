package com.brainstation23.ecommerce.ecommerce.persistence.entity;

import com.brainstation23.ecommerce.ecommerce.constant.ColumnConstant;
import com.brainstation23.ecommerce.ecommerce.constant.EntityConstant;
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
    @Size(max = 50, message = "Product Name Max 50 Characters")
    private String name;

    private BigDecimal unitPrice;

    @NonNull
    @NotBlank
    @Size(max = 200, message = "Product DESCRIPTION Max 200 Characters")
    private String description;

    @NonNull
    @NotBlank
    @Size(max = 255, message = "Product IMAGE URL Too large")
    private String imageUrl;

    @ManyToMany(fetch = FetchType.LAZY)
    @Cascade({ PERSIST, REFRESH, MERGE, DETACH})
    @JoinTable(  name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<CategoryEntity> categories = new HashSet<>();
}
