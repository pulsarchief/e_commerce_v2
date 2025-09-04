package com.priyanshu.e_commerce_v2.dto.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.priyanshu.e_commerce_v2.dto.orderItem.OrderItemResponse;
import com.priyanshu.e_commerce_v2.entity.order.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminOrderDetailedResponse {

    Long dbId;

    String orderId;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    OrderStatus status;

    BigDecimal totalAmount;

    Integer totalItems;

    String paymentId;

    Long userId;

    List<OrderItemResponse> items;
}
