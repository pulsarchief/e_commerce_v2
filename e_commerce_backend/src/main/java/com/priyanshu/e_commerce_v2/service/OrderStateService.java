package com.priyanshu.e_commerce_v2.service;

import org.springframework.stereotype.Service;

import com.priyanshu.e_commerce_v2.entity.order.OrderStatus;
import com.priyanshu.e_commerce_v2.entity.order.Orders;
import com.priyanshu.e_commerce_v2.entity.orderItem.OrderItem;
import com.priyanshu.e_commerce_v2.entity.product.Product;
import com.priyanshu.e_commerce_v2.exception.InvalidOrderStatusTransition;
import com.priyanshu.e_commerce_v2.repository.OrderRepository;
import com.priyanshu.e_commerce_v2.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderStateService {

    private final ProductRepository productRepository;
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final MailService mailService;

    public void changeStatus(Orders order, OrderStatus newStatus) {

        OrderStatus oldStatus = order.getStatus();

        if (!validTransition(oldStatus, newStatus)) {
            throw new InvalidOrderStatusTransition("Cannot transition from " + oldStatus + " to " + newStatus);
        }

        if (newStatus == OrderStatus.FAILED || newStatus == OrderStatus.CANCELLED) {
            handleFailedCancelled(order);
        }

        order.setStatus(newStatus);

        orderRepository.save(order);

        String message = "Your xyz order , with id " + order.getOrderId() + " has been " + order.getStatus();
        mailService.sendMail(order.getUser().getEmail(), "Your Order Status Changed", message);

    }

    public boolean validTransition(OrderStatus oldStatus, OrderStatus newStatus) {
        return switch (oldStatus) {
            case PENDING ->
                newStatus == OrderStatus.PAID || newStatus == OrderStatus.CANCELLED || newStatus == OrderStatus.FAILED;
            case PAID -> newStatus == OrderStatus.SHIPPED || newStatus == OrderStatus.CANCELLED;
            case SHIPPED -> newStatus == OrderStatus.COMPLETED;
            case COMPLETED, CANCELLED, FAILED -> false;
        };
    }

    public void handleFailedCancelled(Orders order) {

        for (OrderItem orderItem : order.getOrderItems()) {

            Product product = productService.findActiveProduct(orderItem.getProduct().getId());

            product.setStock(product.getStock() + orderItem.getQuantity());

            productRepository.save(product);

        }

    }

}
