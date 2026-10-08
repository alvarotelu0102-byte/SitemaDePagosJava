package com.payments.entities;


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

    public void setCardNumber(String cardNumber) {
        CardNumber = cardNumber;
    }

    public String getHolderNumber() {
        return HolderNumber;
    }

    public void setHolderNumber(String holderNumber) {
        HolderNumber = holderNumber;
    }

    public double getCreditLimit() {
        return CreditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        CreditLimit = creditLimit;
    }

    @Override
    public String toString() {
        return "CreditCardPayment{" +
                "CardNumber='" + CardNumber + '\'' +
                ", HolderNumber='" + HolderNumber + '\'' +
                ", CreditLimit=" + CreditLimit +
                "} " + super.toString();
    }

    @Override
    public PaymentStatus processPayment() {
        if (getMonto()>=getCreditLimit()){
            setStatus(PaymentStatus.REJECTED);
            System.out.println("Fundos insuficientes ");
        } else {setStatus(PaymentStatus.APPROVED);
            System.out.println("Credito aprovado ");


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
