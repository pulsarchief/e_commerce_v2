package com.priyanshu.e_commerce_v2.scheduler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.priyanshu.e_commerce_v2.entity.order.OrderStatus;
import com.priyanshu.e_commerce_v2.entity.order.Orders;
import com.priyanshu.e_commerce_v2.repository.OrderRepository;
import com.priyanshu.e_commerce_v2.service.OrderStateService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderStatusTransitionScheduler {

    private final OrderRepository orderRepository;
    private final OrderStateService orderStateService;

    @Scheduled(cron = "0 0 * * * *")
    public void processOrderTransitions() {

        LocalDateTime limit = LocalDateTime.now().minusHours(6);

        List<Orders> pendingOrders = orderRepository.findWithDetailsByStatusAndUpdatedAtBefore(OrderStatus.PENDING,
                limit);

        for (Orders orders : pendingOrders) {

            orderStateService.changeStatus(orders, OrderStatus.FAILED);

        }

        List<Orders> paidOrders = orderRepository.findWithDetailsByStatusAndUpdatedAtBefore(OrderStatus.PAID,
                limit);

        for (Orders orders : paidOrders) {

            orderStateService.changeStatus(orders, OrderStatus.SHIPPED);

        }

        List<Orders> shippedOrders = orderRepository.findWithDetailsByStatusAndUpdatedAtBefore(OrderStatus.SHIPPED,
                limit);

        for (Orders orders : shippedOrders) {

            orderStateService.changeStatus(orders, OrderStatus.COMPLETED);

        }

    }

}
