package com.brainstation23.ecommerce.ecommerce.persistence.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantity;

    private BigDecimal unitPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

}
