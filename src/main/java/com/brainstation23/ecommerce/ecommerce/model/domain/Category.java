package com.brainstation23.ecommerce.ecommerce.model.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
public class Category {
    private UUID id;
    private String categoryName;
}
