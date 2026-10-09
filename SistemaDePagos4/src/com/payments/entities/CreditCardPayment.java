package com.payments.entities;


import com.payments.exceptions.InsufficientFundsException;
import com.payments.exceptions.InvalidPaymentException;
import com.payments.interfaces.Refundable;
//SE CREA CLASE QUE HEREDA DE PAYMENT
public class CreditCardPayment extends Payment implements Refundable {

    private String CardNumber;
    private String HolderNumber;
    private double CreditLimit;


    //SE CREO CONSTRUCTOR

    public CreditCardPayment(double monto, String cardNumber, String holderNumber, double creditLimit) {
        super(monto);
        CardNumber = cardNumber;
        HolderNumber = holderNumber;
        CreditLimit = creditLimit;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String cardNumber) {this.CardNumber = cardNumber;
    }

    public String getHolderNumber() {
        return HolderNumber;
    }

    public void setHolderNumber(String holderNumber) {
        this.HolderNumber = holderNumber;
    }

    public double getCreditLimit() {
        return CreditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.CreditLimit = creditLimit;
    }

    // Mostrar informacion
    @Override
    public String toString() {
        return "CreditCardPayment{" +
                "CardNumber='****" +
                (CardNumber != null && CardNumber.length() >= 4
                        ? CardNumber.substring(CardNumber.length() - 4)
                        : "****") + '\'' +
                ", HolderNumber='" + HolderNumber + '\'' +
                ", CreditLimit=" + CreditLimit +
                "} " + super.toString();
    }

    //Procesar pago
    @Override
    public PaymentStatus processPayment()
            throws InsufficientFundsException, InvalidPaymentException {

        // 1. Validar que el pago este pendiente
        if (getStatus() != PaymentStatus.REJECTED) {
            throw new InvalidPaymentException("El pago ya fue procesado");
        }

        // Validar el monto
        if (!Double.isFinite(getMonto()) || getMonto() <= 0) {

            setStatus(PaymentStatus.REJECTED);

            throw new InvalidPaymentException(
                    "El monto debe ser mayor a cero"
            );
        }

        // 3. Validar datos de la tarjeta
        if (CardNumber == null || CardNumber.isBlank()
                || HolderNumber == null || HolderNumber.isBlank()) {

            setStatus(PaymentStatus.REJECTED);

            throw new InvalidPaymentException(
                    "Datos de tarjeta incompletos"
            );
        }

        // 4. Validar limite de credito
        if (!Double.isFinite(CreditLimit) || CreditLimit < 0) {

            setStatus(PaymentStatus.REJECTED);

            throw new InvalidPaymentException(
                    "Limite de credito invalido"
            );
        }

        // 5. Aprobar o rechazar el pago
        if (getMonto() <= CreditLimit) {

            CreditLimit -= getMonto();

            setStatus(PaymentStatus.APPROVED);

            System.out.println("Credito aprobado");

        } else {

            setStatus(PaymentStatus.REJECTED);

            throw new InsufficientFundsException(
                    "Limite de credito insuficiente"
            );
        }

        return getStatus();
    }

    @Override
    public void refund() {

        if (getStatus()== PaymentStatus.APPROVED){
            CreditLimit += getMonto();
            setStatus(PaymentStatus.REJECTED);
            System.out.println("Reembolso Realizado");
        } else{

            System.out.println("El reembolso no fue aprobado");
        }

    }//Refund
}//Public cass
