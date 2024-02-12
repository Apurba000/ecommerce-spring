package com.brainstation23.ecommerce.ecommerce.model.dto.cartItem;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    private List<CartItemResponse> cartItemList;

//    public BigDecimal calculateTotal(){
//        BigDecimal total = BigDecimal.ZERO;
//        for (CartItemResponse item : cartItemList){
//            BigDecimal unitPrice = item.getProduct().getUnitPrice();
//            int quantity = item.getQuantity();
//            total = total.add(unitPrice.multiply(BigDecimal.valueOf(quantity)));
//        }
//        return total;
//    }
}
