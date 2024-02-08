package com.brainstation23.ecommerce.ecommerce.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class CartItem {
    private UUID id;
    private Product product;
    private int quantity;
    private Timestamp date;
}
