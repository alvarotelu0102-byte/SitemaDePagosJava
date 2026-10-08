package com.payments.exceptions;

public class InvalidPaymentExcepcion extends RuntimeException {
    public InvalidPaymentExcepcion(String message) {
        super(message);
    }
}
