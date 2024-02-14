package com.brainstation23.ecommerce.ecommerce.persistence.entity;


import com.brainstation23.ecommerce.ecommerce.constant.EntityConstant;
import com.brainstation23.ecommerce.ecommerce.model.enums.ERole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.JdbcTypeCode;
import org.springframework.security.core.GrantedAuthority;

import java.sql.Types;
import java.util.UUID;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = EntityConstant.ROLES)
public class RoleEntity implements GrantedAuthority {
        @Id @GeneratedValue
        @JdbcTypeCode(Types.VARCHAR)
        private UUID id;

        @Column(columnDefinition = "VARCHAR(50)")
        private String name;

        @Override
        public String getAuthority() {
                return name;
        }
}
