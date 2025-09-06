package com.priyanshu.e_commerce_v2.exception;

public class InvalidOrderStatusTransition extends RuntimeException {
    public InvalidOrderStatusTransition(String message) {
        super(message);
    }
}
