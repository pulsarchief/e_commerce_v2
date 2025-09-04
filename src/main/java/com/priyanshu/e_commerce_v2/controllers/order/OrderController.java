package com.priyanshu.e_commerce_v2.controllers.order;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.priyanshu.e_commerce_v2.dto.order.OrderDetailedResponse;
import com.priyanshu.e_commerce_v2.dto.orderItem.OrderItemRequest;
import com.priyanshu.e_commerce_v2.dto.orderItem.OrderItemResponse;
import com.priyanshu.e_commerce_v2.entity.order.Orders;
import com.priyanshu.e_commerce_v2.exception.UnAuthorizedAccessException;
import com.priyanshu.e_commerce_v2.security.CustomUserDetails;
import com.priyanshu.e_commerce_v2.service.OrderService;
import com.priyanshu.e_commerce_v2.util.mappers.OrderItemMappers;
import com.priyanshu.e_commerce_v2.util.mappers.OrderMappers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderMappers orderMappers;
    private final OrderItemMappers orderItemMappers;

    @PostMapping("")
    public ResponseEntity<OrderDetailedResponse> addOrder(@AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody @Valid List<OrderItemRequest> items) {

        Orders order = orderService.addOrder(userDetails.getId(), items);
        List<OrderItemResponse> itemResponse = order.getOrderItems().stream().map(x -> orderItemMappers.toItemResponse(x))
                .toList();
        return new ResponseEntity<>(orderMappers.toOrderDetailedResponse(order, itemResponse), HttpStatus.CREATED);

    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDetailedResponse> getOrder(@AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long orderId) {

        Orders order = orderService.getOrder(orderId);

        if (!order.getUser().getId().equals(userDetails.getId())) {
            throw new UnAuthorizedAccessException("Unauthorized Access");
        }

        List<OrderItemResponse> itemResponse = order.getOrderItems().stream().map(x -> orderItemMappers.toItemResponse(x))
                .toList();

        return new ResponseEntity<>(orderMappers.toOrderDetailedResponse(order, itemResponse), HttpStatus.OK);
    }
}