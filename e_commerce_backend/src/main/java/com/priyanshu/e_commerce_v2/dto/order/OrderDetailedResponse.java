package com.priyanshu.e_commerce_v2.dto.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.priyanshu.e_commerce_v2.dto.orderItem.OrderItemResponse;
import com.priyanshu.e_commerce_v2.entity.order.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailedResponse {

    String orderId;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    OrderStatus status;

    BigDecimal totalAmount;

    Integer totalItems;

    String paymentId;

    List<OrderItemResponse> items;
}
