package com.priyanshu.e_commerce_v2.dto.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.priyanshu.e_commerce_v2.entity.order.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderSummaryResponse {

    String orderId;

    LocalDateTime creationAt;

    OrderStatus status;

    BigDecimal totalAmount;

}
