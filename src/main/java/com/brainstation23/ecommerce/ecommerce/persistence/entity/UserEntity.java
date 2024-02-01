package com.brainstation23.ecommerce.ecommerce.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import static com.brainstation23.ecommerce.ecommerce.constant.EntityConstant.USER_TABLE;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = USER_TABLE,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
