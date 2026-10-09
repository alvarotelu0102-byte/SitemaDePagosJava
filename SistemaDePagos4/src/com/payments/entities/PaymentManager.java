package com.payments.entities;

import java.util.ArrayList;

public class PaymentManager {

    // Atributo
    private ArrayList<Payment> payments;

    // constructor
    public PaymentManager(){
        this.payments = new ArrayList<>();
    }//payment

    // Registrar pago
    public void registerPayment(Payment payment) {

        if (payment == null) {
            System.out.println("No se puede registrar un pago nulo");
            return;
        }

        if (findById(payment.getID()) != null) {
            System.out.println("Error: el ID ya esta registrado");
            return;
        }

        payments.add(payment);

        System.out.println("Pago guardado con ID: "
                + payment.getID());
    }

    // Mostrar pagos
    public void showPayment() {

        if (payments.isEmpty()) {
            System.out.println("No hay pagos");
            return;
        }

        System.out.println("Lista de Pagos Registrados");

        for (Payment p : payments) {

            System.out.println(
                    "ID: " + p.getID()
                            + " | Monto: $" + p.getMonto()
                            + " | Estado: " + p.getStatus()
            );
        }
    }

    // Buscar pago por ID
    public Payment findById(int ID) {

        for (Payment p : payments) {

            if (p.getID() == ID) {
                return p;
            }
        }

        return null;
    }

    // Sumar pagos aprobados
    public double getTotal() {

        double total = 0.0;

        for (Payment p : payments) {

            if (p.getStatus() == PaymentStatus.APPROVED) {
                total += p.getMonto();
            }
        }

        return total;
    }

    // Contar pagos procesados
    public int getTotalProcessedPayments() {

        int contador = 0;

        for (Payment p : payments) {

            if (p.getStatus() != PaymentStatus.PENDING) {
                contador++;
            }
        }

        return contador;
    }

    // Contar pagos registrados
    public int getTotalRegisteredPayments() {
        return payments.size();
    }
}