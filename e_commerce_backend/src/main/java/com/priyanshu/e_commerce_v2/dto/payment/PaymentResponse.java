package com.priyanshu.e_commerce_v2.dto.payment;

import java.time.LocalDateTime;
import java.util.UUID;

import com.priyanshu.e_commerce_v2.entity.payment.PaymentMode;
import com.priyanshu.e_commerce_v2.entity.payment.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentResponse {

    UUID paymentId;

    UUID orderId;

    PaymentMode mode;

    LocalDateTime time;

    PaymentStatus status;
}
