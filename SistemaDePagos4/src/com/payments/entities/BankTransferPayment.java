
package com.payments.entities;

import com.payments.exceptions.InsufficientFundsException;
import com.payments.exceptions.InvalidPaymentException;

public class BankTransferPayment extends Payment {

    // Atributos
    private double balance;
    private String accountNumber;
    private String bankName;

    // Constructor
    public BankTransferPayment(double monto, double balance,
                               String accountNumber, String bankName) {

        super(monto);

        this.balance = balance;
        this.accountNumber = accountNumber;
        this.bankName = bankName;
    }

    // Getter y setter del saldo
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Getter del numero de cuenta
    public String getAccountNumber() {
        return accountNumber;
    }

    // Getter del banco
    public String getBankName() {
        return bankName;
    }

    // Mostrar informacion del pago
    @Override
    public String toString() {
        return "BankTransferPayment{" +
                "bankName='" + bankName + '\'' +
                ", balance=" + balance +
                "} " + super.toString();
    }

    // Procesar transferencia
    @Override
    public PaymentStatus processPayment()
            throws InsufficientFundsException, InvalidPaymentException {

        // 1. Validar estado
        if (getStatus() != PaymentStatus.PENDING) {

            throw new InvalidPaymentException(
                    "El pago ya fue procesado"
            );
        }

        // 2. Validar monto
        if (!Double.isFinite(getMonto()) || getMonto() <= 0) {

            setStatus(PaymentStatus.REJECTED);

            throw new InvalidPaymentException(
                    "El monto debe ser mayor a cero"
            );
        }

        // 3. Validar datos bancarios
        if (accountNumber == null || accountNumber.isBlank()
                || bankName == null || bankName.isBlank()) {

            setStatus(PaymentStatus.REJECTED);

            throw new InvalidPaymentException(
                    "Datos bancarios incompletos"
            );
        }

        // 4. Validar saldo
        if (!Double.isFinite(balance) || balance < 0) {

            setStatus(PaymentStatus.REJECTED);

            throw new InvalidPaymentException(
                    "El saldo disponible es invalido"
            );
        }

        // 5. Aprobar o rechazar
        if (balance >= getMonto()) {

            balance -= getMonto();

            setStatus(PaymentStatus.APPROVED);

            System.out.println("Transferencia aprobada");

        } else {

            setStatus(PaymentStatus.REJECTED);

            throw new InsufficientFundsException(
                    "Saldo insuficiente para realizar la transferencia"
            );
        }

        return getStatus();
    }
}
