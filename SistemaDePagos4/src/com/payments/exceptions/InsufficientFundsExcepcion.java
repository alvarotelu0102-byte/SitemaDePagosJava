package com.payments.exceptions;

public class InsufficientFundsExcepcion extends RuntimeException {
    public InsufficientFundsExcepcion(String message) {
        super(message);
    }
}
