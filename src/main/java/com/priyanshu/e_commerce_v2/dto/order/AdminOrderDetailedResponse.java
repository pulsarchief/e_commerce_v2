package com.priyanshu.e_commerce_v2.dto.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.cglib.core.Local;

import com.priyanshu.e_commerce_v2.dto.orderItem.OrderItemResponse;
import com.priyanshu.e_commerce_v2.entity.order.OrderStatus;

public class AdminOrderDetailedResponse {

    String dbId;

    String orderId;

    LocalDateTime createdAt;

    Local updatedAt;

    OrderStatus status;

    BigDecimal totalAmount;

    Integer totalItems;

    String paymentId;

    String userId;

    List<OrderItemResponse> items;
}
