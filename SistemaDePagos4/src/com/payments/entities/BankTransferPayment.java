package com.payments.entities;

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
    public PaymentStatus processPayment() {
        if(balance >= getMonto()) {
            setStatus(PaymentStatus.APPROVED);
            System.out.println("Pago aprovado ");
        } else {
            setStatus(PaymentStatus.REJECTED);
            System.out.println("Pago rechazado ");
        }
        return getStatus();
    }
}
