package com.priyanshu.e_commerce_v2.controllers.payment;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.priyanshu.e_commerce_v2.dto.payment.PaymentResponse;
import com.priyanshu.e_commerce_v2.entity.payment.PaymentStatus;
import com.priyanshu.e_commerce_v2.mappers.PaymentMappers;
import com.priyanshu.e_commerce_v2.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentMappers paymentMappers;

    @PostMapping("/{orderId}")
    public ResponseEntity<PaymentResponse> paymentForOrder(@PathVariable UUID orderId) {

        PaymentResponse response = paymentMappers.toPaymentResponse(paymentService.makePayment(orderId));

        if (response.getStatus().equals(PaymentStatus.SUCCESSFUL)) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }

    }

}
