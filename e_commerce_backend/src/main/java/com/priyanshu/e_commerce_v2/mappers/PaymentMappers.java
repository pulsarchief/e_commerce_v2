package com.priyanshu.e_commerce_v2.mappers;

import org.springframework.stereotype.Component;

import com.priyanshu.e_commerce_v2.dto.payment.PaymentResponse;
import com.priyanshu.e_commerce_v2.entity.payment.Payment;

@Component
public class PaymentMappers {

    public PaymentResponse toPaymentResponse(Payment payment) {

        PaymentResponse response = new PaymentResponse();

        response.setMode(payment.getMode());
        response.setOrderId(payment.getOrder().getOrderId());
        response.setPaymentId(payment.getPaymentId());
        response.setStatus(payment.getStatus());
        response.setTime(payment.getPaymentTime());

        return response;
    }
}
