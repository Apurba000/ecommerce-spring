package com.brainstation23.ecommerce.ecommerce.persistence.entity;


import com.brainstation23.ecommerce.ecommerce.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @CreatedDate
    @Column(name = "order_date")
    private Timestamp orderDate;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(50)")
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_address_id")
    private AddressEntity deliveryAddress;
}
