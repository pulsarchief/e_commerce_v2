package com.priyanshu.e_commerce_v2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.priyanshu.e_commerce_v2.entity.order.Orders;
import com.priyanshu.e_commerce_v2.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Orders> findTop3ForUser(Long userId) {

        return orderRepository.findTop3WithDetailsByUserIdOrderByCreatedAtDesc(userId);

    }

}
