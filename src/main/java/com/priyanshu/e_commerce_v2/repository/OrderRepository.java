package com.priyanshu.e_commerce_v2.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.priyanshu.e_commerce_v2.entity.order.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long>, JpaSpecificationExecutor<Orders> {

    @EntityGraph(attributePaths = { "payment", "orderItems", "user", "orderItems.product" })
    List<Orders> findTop3WithDetailsByUserIdOrderByCreatedAtDesc(Long userId);

    @EntityGraph(attributePaths = { "payment", "orderItems", "user", "orderItems.product" })
    Optional<Orders> findWithDetailsByDbId(Long dbId);

    @EntityGraph(attributePaths = { "payment", "orderItems", "user", "orderItems.product" })
    List<Orders> findAllByUserId(Long userId);

    @EntityGraph(attributePaths = { "payment", "orderItems", "user", "orderItems.product" })
    Optional<Orders> findWithDetailsByOrderId(UUID orderId);

    @Override
    @EntityGraph(attributePaths = { "payment", "orderItems", "user", "orderItems.product" })
    List<Orders> findAll();
}
