package com.payments.entities;

import java.net.IDN;

//Creamos superclase abstracta
public abstract class Payment {
    //Creamos atributos de clase
    private int ID;
    private double Monto;
    private PaymentStatus status;

    public Payment(int ID, double monto, PaymentStatus status) {
        this.ID = ID;
        Monto = monto;
        this.status = status;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getMonto() {
        return Monto;
    }

    public void setMonto(double monto) {
        Monto = monto;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "ID=" + ID +
                ", Monto=" + Monto +
                ", status=" + status +
                '}';
    }

    protected Payment(double monto) {
        this.ID = ID;
        this.Monto = monto;
        this.status = PaymentStatus.PENDING;
    } //Metodo que devuelve el ID, MONTO, ESTADO DE PAGO

    public abstract PaymentStatus processPayment();


}//main
