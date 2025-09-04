package com.priyanshu.e_commerce_v2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.priyanshu.e_commerce_v2.entity.order.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long>, JpaSpecificationExecutor<Orders> {

    @EntityGraph(attributePaths = { "payment", "products","user" })
    List<Orders> findTop3WithDetailsByUserIdOrderByCreatedAtDesc(Long userId);
}
