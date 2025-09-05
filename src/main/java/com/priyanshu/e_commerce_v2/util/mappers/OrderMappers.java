package com.priyanshu.e_commerce_v2.util.mappers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.priyanshu.e_commerce_v2.dto.order.AdminOrderDetailedResponse;
import com.priyanshu.e_commerce_v2.dto.order.AdminOrderSummaryResponse;
import com.priyanshu.e_commerce_v2.dto.order.OrderDetailedResponse;
import com.priyanshu.e_commerce_v2.dto.order.OrderSummaryResponse;
import com.priyanshu.e_commerce_v2.dto.orderItem.OrderItemResponse;
import com.priyanshu.e_commerce_v2.entity.order.Orders;
import com.priyanshu.e_commerce_v2.entity.orderItem.OrderItem;
import com.priyanshu.e_commerce_v2.entity.user.Users;

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
        dto.setDbId(order.getDbId());
        dto.setUserId(order.getUser().getId());
        dto.setUpdatedAt(order.getUpdatedAt());

        return dto;
    }

    public OrderDetailedResponse toOrderDetailedResponse(Orders order, List<OrderItemResponse> items) {

        OrderDetailedResponse details = new OrderDetailedResponse();

        details.setOrderId(order.getOrderId().toString());
        details.setCreatedAt(order.getCreatedAt());
        details.setUpdatedAt(order.getUpdatedAt());
        details.setStatus(order.getStatus());
        details.setTotalAmount(order.getTotalAmount());
        details.setTotalItems(order.getTotalItems());
        if (order.getPayment() != null) {

            details.setPaymentId(order.getPayment().getPaymentId().toString());
        }
        details.setItems(items);

        return details;
    }

    public AdminOrderDetailedResponse toAdminOrderDetailedResponse(Orders order, List<OrderItemResponse> items) {

        AdminOrderDetailedResponse details = new AdminOrderDetailedResponse();

        details.setDbId(order.getDbId());
        details.setOrderId(order.getOrderId().toString());
        details.setCreatedAt(order.getCreatedAt());
        details.setUpdatedAt(order.getUpdatedAt());
        details.setStatus(order.getStatus());
        details.setTotalAmount(order.getTotalAmount());
        details.setTotalItems(order.getTotalItems());
        if (order.getPayment() != null) {

            details.setPaymentId(order.getPayment().getPaymentId().toString());
        }
        details.setUserId(order.getUser().getId());
        details.setItems(items);

        return details;
    }

    public Orders toOrder(List<OrderItem> items, Users user) {

        Orders order = new Orders();

        for (OrderItem orderItem : items) {
            orderItem.setOrder(order);
        }

        order.setOrderItems(items);

        order.setTotalItems(
                items.stream().map(x -> x.getQuantity()).mapToInt(x -> Integer.valueOf(x)).sum());
        order.setTotalAmount(
                items.stream().map(x -> x.getTotalPrice()).reduce(BigDecimal.ZERO, BigDecimal::add));

        order.setPayment(null);
        order.setUser(user);

        return order;
    }
}
