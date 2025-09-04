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
public class AdminOrderSummaryResponse {

    Long dbId;

    String orderId;

    LocalDateTime creationAt;

    LocalDateTime updatedAt;

    OrderStatus status;

    BigDecimal totalAmount;

    Long userId;

}
