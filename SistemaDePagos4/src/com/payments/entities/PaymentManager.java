package com.payments.entities;

import java.util.ArrayList;

public class PaymentManager {
    private ArrayList<Payment> payments;

    public PaymentManager(){
        this.payments = new ArrayList<>();
    }//payment

    public void registerPayment(Payment payment){
        payments.add(payment);
        System.out.println("Pago guardado con ID " + payment.getID());
    }//register

    public void showPayment() {
        if (payments.isEmpty()) {
            System.out.println("No hay pagos");
            return;
        }//if
        for (Payment p : payments) {
            System.out.println("Lista de Pagos Registrados");
            System.out.println(" ID:" + p.getID() + "Monto: " + p.getMonto());
        }
    }// show

    public Payment findById(int ID){
        for(Payment p : payments){
            if (p.getID() == ID) {
                return p;
            }
        } return null;
    }// find

    public double getTotal(){
        double total = 0.0;
        for(Payment p : payments){
            if(p.getStatus() == PaymentStatus.APPROVED){
                total += p.getMonto();
            }
        }
      return total;
    }
}//PaymentManager

