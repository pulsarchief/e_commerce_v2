package com.priyanshu.e_commerce_v2.dto.orderItem;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {

    Long productId;

    String productName;

    Integer quantity;

    BigDecimal unitPrice;

    BigDecimal totalPrice;
}
