package com.brainstation23.ecommerce.ecommerce.persistence.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.sql.Date;

import static com.brainstation23.ecommerce.ecommerce.constant.EntityConstant.ORDER_TABLE;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = ORDER_TABLE)
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "order_date")
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    @NonNull
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_address_id")
    private AddressEntity deliveryAddress;
}
