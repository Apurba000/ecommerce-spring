package com.brainstation23.ecommerce.ecommerce.persistence.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;


@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart_item")
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @NotBlank
    private int quantity;

    @CreatedDate
    private Timestamp date;

}
