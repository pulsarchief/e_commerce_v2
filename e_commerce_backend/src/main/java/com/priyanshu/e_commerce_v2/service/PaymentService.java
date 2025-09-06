package com.priyanshu.e_commerce_v2.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.priyanshu.e_commerce_v2.entity.order.OrderStatus;
import com.priyanshu.e_commerce_v2.entity.order.Orders;
import com.priyanshu.e_commerce_v2.entity.payment.Payment;
import com.priyanshu.e_commerce_v2.entity.payment.PaymentStatus;
import com.priyanshu.e_commerce_v2.exception.OrderAlreadyPaidException;
import com.priyanshu.e_commerce_v2.exception.PaymentNotFoundException;
import com.priyanshu.e_commerce_v2.repository.OrderRepository;
import com.priyanshu.e_commerce_v2.repository.PaymentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentService {

    private final OrderService orderService;
    private final OrderStateService orderStateService;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    public Payment makePayment(UUID orderId) {

        Orders order = orderService.getOrder(orderId);

        if (order.getStatus() != OrderStatus.PENDING) {
            throw new OrderAlreadyPaidException(
                    "Cannot make payment for " + orderId + " Order is " + order.getStatus());
        }

        int random = (int) (Math.random() * 10 + 1);

        Payment payment = new Payment();
        payment.setOrder(order);
        order.setPayment(payment);

        if (random <= 7) {
            payment.setStatus(PaymentStatus.SUCCESSFUL);
            orderStateService.changeStatus(order, OrderStatus.PAID);
        } else {
            payment.setStatus(PaymentStatus.FAILED);
            orderStateService.changeStatus(order, OrderStatus.FAILED);
        }

        order = orderRepository.save(order);

        return paymentRepository.findById(order.getPayment().getId())
                .orElseThrow(() -> new PaymentNotFoundException("Payment id not found"));
    }

}
