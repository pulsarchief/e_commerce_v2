package com.priyanshu.e_commerce_v2.controllers.order;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.priyanshu.e_commerce_v2.dto.order.AdminOrderDetailedResponse;
import com.priyanshu.e_commerce_v2.dto.order.AdminOrderSummaryResponse;
import com.priyanshu.e_commerce_v2.dto.orderItem.OrderItemResponse;
import com.priyanshu.e_commerce_v2.entity.order.Orders;
import com.priyanshu.e_commerce_v2.security.CustomUserDetails;
import com.priyanshu.e_commerce_v2.service.OrderService;
import com.priyanshu.e_commerce_v2.util.mappers.OrderItemMappers;
import com.priyanshu.e_commerce_v2.util.mappers.OrderMappers;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/order")
@RequiredArgsConstructor
public class AdminOrderController {

    private final OrderService orderService;
    private final OrderItemMappers orderItemMappers;
    private final OrderMappers orderMappers;

    @GetMapping("/{dbId}")
    public ResponseEntity<AdminOrderDetailedResponse> getOrder(@PathVariable Long dbId) {

        Orders order = orderService.getOrder(dbId);

        List<OrderItemResponse> itemResponse = order.getOrderItems().stream()
                .map(x -> orderItemMappers.toItemResponse(x))
                .toList();

        return new ResponseEntity<>(orderMappers.toAdminOrderDetailedResponse(order, itemResponse), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<AdminOrderSummaryResponse>> getAllOrders(
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        List<AdminOrderSummaryResponse> userOrders = orderService.getAllOrders().stream()
                .map(x -> orderMappers.toAdminOrderSummaryResponse(x)).toList();

        return new ResponseEntity<>(userOrders, HttpStatus.OK);

    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<AdminOrderSummaryResponse>> getAllOrderForUser(@PathVariable Long userId) {

        List<AdminOrderSummaryResponse> userOrders = orderService.getAllOrders().stream()
                .map(x -> orderMappers.toAdminOrderSummaryResponse(x)).toList();

        return new ResponseEntity<>(userOrders, HttpStatus.OK);
    }

}
