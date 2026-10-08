package com.payments.entities;

import com.payments.exceptions.InsufficientFundsException;
import com.payments.exceptions.InvalidPaymentException;
import com.payments.interfaces.Refundable;

import java.io.IOException;
import java.nio.CharBuffer;

public class PayPalPayment  extends Payment implements Refundable {
    private final String email;
    private double paypalBalance;


    public PayPalPayment(double monto, double paypalBalance, String email) {
        super(monto);
        this.paypalBalance = paypalBalance;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public double getPaypalBalance() {
        return paypalBalance;
    }

    public void setPaypalBalance(double paypalBalance) {
        this.paypalBalance = paypalBalance;
    }

    @Override
    public String toString() {
        return "PayPalPayment{" +
                "email='" + email + '\'' +
                ", paypalBalance=" + paypalBalance +
                '}';
    }

    @Override
    public PaymentStatus processPayment() throws InsufficientFundsException, InvalidPaymentException {
        if(paypalBalance >= getMonto()){
            setStatus(PaymentStatus.APPROVED);
            System.out.println("Pago aprovado ");
        } else {
            setStatus(PaymentStatus.REJECTED);
            throw new InsufficientFundsException("Pago rechazado ");
        }
        return getStatus();
    }

    @Override
    public void refund() {
        if (getStatus()== PaymentStatus.APPROVED){
           paypalBalance += getMonto();
            setStatus(PaymentStatus.REJECTED);
            System.out.println("Reembolso Realizado");
        } else{
            System.out.println("El reembolso no fue aprobado");
        }

    }
}
