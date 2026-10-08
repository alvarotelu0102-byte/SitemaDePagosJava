package com.payments.entities;

import com.payments.exceptions.InsufficientFundsException;
import com.payments.exceptions.InvalidPaymentException;

public class BankTransferPayment  extends Payment {
    private double balance;

    protected BankTransferPayment(double monto) {
        super(monto);
    }

    public BankTransferPayment(double monto, double balance) {
        super(monto);
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BankTransferPayment{" +
                "balance=" + balance +
                '}';
    }

    @Override
    public PaymentStatus processPayment() throws InsufficientFundsException, InvalidPaymentException {
        if(balance >= getMonto()) {
            setStatus(PaymentStatus.APPROVED);
            System.out.println("Pago aprovado ");
        } else {
            setStatus(PaymentStatus.REJECTED);
           throw new InsufficientFundsException("Pago rechazado");
        }
        return getStatus();
    }
}
