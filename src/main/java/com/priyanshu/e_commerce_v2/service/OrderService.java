package com.priyanshu.e_commerce_v2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.priyanshu.e_commerce_v2.dto.orderItem.OrderItemRequest;
import com.priyanshu.e_commerce_v2.entity.order.Orders;
import com.priyanshu.e_commerce_v2.entity.orderItem.OrderItem;
import com.priyanshu.e_commerce_v2.entity.product.Product;
import com.priyanshu.e_commerce_v2.entity.user.Users;
import com.priyanshu.e_commerce_v2.exception.InsufficientQuantityException;
import com.priyanshu.e_commerce_v2.exception.OrderNotFoundException;
import com.priyanshu.e_commerce_v2.exception.ProductNotFoundException;
import com.priyanshu.e_commerce_v2.repository.OrderRepository;
import com.priyanshu.e_commerce_v2.repository.ProductRepository;
import com.priyanshu.e_commerce_v2.util.mappers.OrderItemMappers;
import com.priyanshu.e_commerce_v2.util.mappers.OrderMappers;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductRepository productRepository;
    private final OrderItemMappers orderItemMappers;
    private final OrderMappers orderMappers;

    public List<Orders> findTop3ForUser(Long userId) {

        return orderRepository.findTop3WithDetailsByUserIdOrderByCreatedAtDesc(userId);

    }

    public List<Orders> findAllOrdersForUser(Long userId) {

        return orderRepository.findAllByUserId(userId);

    }

    public Orders addOrder(Long userId, List<OrderItemRequest> productList) {

        Users user = userService.findUserById(userId);

        List<OrderItem> items = new ArrayList<>();

        for (OrderItemRequest item : productList) {

            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Product Not Found"));

            if (product.getStock() < item.getQuantity()) {
                throw new InsufficientQuantityException("Not Enough Quantity Present");
            }

            items.add(orderItemMappers.toItem(item.getQuantity(), product));

            productRepository.save(product);
        }

        Orders order = orderMappers.toOrder(items, user);

        return getOrder(orderRepository.save(order).getDbId());
    }

    public Orders getOrder(Long dbId) {
        return orderRepository.findWithDetailsByDbId(dbId)
                .orElseThrow(() -> new OrderNotFoundException("Order with" + dbId + " id not found"));
    }

    public Orders getOrder(UUID orderId) {
        return orderRepository.findWithDetailsByOrderId(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order with" + orderId + " id not found"));
    }

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }
}
