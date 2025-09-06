package com.priyanshu.e_commerce_v2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.priyanshu.e_commerce_v2.entity.payment.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Override
    @EntityGraph(attributePaths = { "order" })
    Optional<Payment> findById(Long id);

}
