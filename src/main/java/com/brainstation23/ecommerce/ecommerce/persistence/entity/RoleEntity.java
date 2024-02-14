package com.brainstation23.ecommerce.ecommerce.persistence.entity;


import com.brainstation23.ecommerce.ecommerce.constant.EntityConstant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = EntityConstant.ROLES)
public class RoleEntity{
        @Id @GeneratedValue
        @JdbcTypeCode(Types.VARCHAR)
        private UUID id;

        @Column(columnDefinition = "VARCHAR(50)")
        private String name;
}
