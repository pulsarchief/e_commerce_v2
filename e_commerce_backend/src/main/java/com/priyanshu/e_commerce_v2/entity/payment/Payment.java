package com.priyanshu.e_commerce_v2.entity.payment;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.priyanshu.e_commerce_v2.entity.order.Orders;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true, updatable = false)
    UUID paymentId = UUID.randomUUID();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "payment")
    Orders order;

    @Enumerated(value = EnumType.STRING)
    PaymentMode mode = PaymentMode.ONLINE;

    @CreationTimestamp
    LocalDateTime paymentTime;

    @Enumerated(value = EnumType.STRING)
    PaymentStatus status;
}
