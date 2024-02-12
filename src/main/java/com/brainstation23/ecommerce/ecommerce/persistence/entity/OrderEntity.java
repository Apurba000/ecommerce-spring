package com.brainstation23.ecommerce.ecommerce.persistence.entity;


import com.brainstation23.ecommerce.ecommerce.constant.ColumnConstant;
import com.brainstation23.ecommerce.ecommerce.constant.EntityConstant;
import com.brainstation23.ecommerce.ecommerce.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.JdbcTypeCode;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.hibernate.annotations.CascadeType.*;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = EntityConstant.ORDERS)
public class OrderEntity {
    @Id
    @GeneratedValue
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;


    @Column(name = ColumnConstant.TOTAL_AMOUNT)
    private BigDecimal totalAmount;

    @CreatedDate
    @Column(name = ColumnConstant.ORDER_DATE)
    private Timestamp orderDate;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(50)")
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ColumnConstant.DELIVERY_ADDRESS_ID)
    private AddressEntity deliveryAddress;

    @OneToMany(mappedBy = "order", orphanRemoval = true)
    @Cascade({ALL})
    private Set<OrderItemEntity> items = new HashSet<>();
}
