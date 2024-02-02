package com.brainstation23.ecommerce.ecommerce.persistence.entity;


import com.brainstation23.ecommerce.ecommerce.model.enums.ERole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class RoleEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Enumerated(EnumType.STRING)
        @Column(columnDefinition = "VARCHAR(50)")
        private ERole name;

}
