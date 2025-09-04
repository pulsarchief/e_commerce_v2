package com.priyanshu.e_commerce_v2.util.mappers;

import org.springframework.stereotype.Component;

import com.priyanshu.e_commerce_v2.dto.order.AdminOrderSummaryResponse;
import com.priyanshu.e_commerce_v2.dto.order.OrderSummaryResponse;
import com.priyanshu.e_commerce_v2.entity.order.Orders;

@Component
public class OrderMappers {

    public OrderSummaryResponse toOrderSummaryResponse(Orders order) {

        OrderSummaryResponse dto = new OrderSummaryResponse();

        dto.setCreationAt(order.getCreatedAt());
        dto.setOrderId(order.getOrderId().toString());
        dto.setStatus(order.getStatus());
        dto.setTotalAmount(order.getTotalAmount());

        return dto;
    }

    public AdminOrderSummaryResponse toAdminOrderSummaryResponse(Orders order) {

        AdminOrderSummaryResponse dto = new AdminOrderSummaryResponse();

        dto.setCreationAt(order.getCreatedAt());
        dto.setOrderId(order.getOrderId().toString());
        dto.setStatus(order.getStatus());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setDbId(order.getId());
        dto.setUserId(order.getUser().getId());
        dto.setUpdatedAt(order.getUpdatedAt());

        return dto;
    }
}
