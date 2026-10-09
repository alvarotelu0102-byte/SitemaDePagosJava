package com.payments;

import com.payments.entities.*;
import com.payments.exceptions.InsufficientFundsException;
import com.payments.exceptions.InvalidPaymentException;
import com.payments.interfaces.Refundable;

public class PaymentApp {

    public static void main(String[] args) {

        // Crear administrador de pagos
        PaymentManager manager = new PaymentManager();

        // Crear diferentes metodos de pago
        Payment tarjeta = new CreditCardPayment(1000, "4111111111111111", "Juan Perez", 5000);

        Payment paypal = new PayPalPayment(800, 2000, "juan@correo.com");

        Payment transferencia = new BankTransferPayment(600, 1500, "1234567890", "Banco Ejemplo");

        // Crear pago que sera rechazado
        Payment pagoRechazado = new PayPalPayment(2000, 500, "ana@correo.com");

        // Registrar todos los pagos
        manager.registerPayment(tarjeta);
        manager.registerPayment(paypal);
        manager.registerPayment(transferencia);
        manager.registerPayment(pagoRechazado);



        // CASO 1 - PAGO EXITOSO


        System.out.println("\n--- CASO 1: PAGO EXITOSO ---");

        try {
            tarjeta.processPayment();

            System.out.println("Estado del pago: " + tarjeta.getStatus());

        } catch (InsufficientFundsException | InvalidPaymentException error) {

            System.out.println("Error: " + error.getMessage());
        }



        // CASO 2 - PAGO RECHAZADO


        System.out.println("\n--- CASO 2: PAGO RECHAZADO ---");

        try {
            pagoRechazado.processPayment();

        } catch (InsufficientFundsException error) {

            System.out.println("Fondos insuficientes: " + error.getMessage());

        } catch (InvalidPaymentException error) {

            System.out.println("Pago invalido: " + error.getMessage());
        }

        System.out.println("El programa continua funcionando");


        // CASO 3 - DIFERENTES METODOS DE PAGO


        System.out.println("\n--- CASO 3: DIFERENTES METODOS ---");

        try {

            paypal.processPayment();

            transferencia.processPayment();

            System.out.println("Tarjeta: " + tarjeta.getStatus());

            System.out.println("PayPal: " + paypal.getStatus());

            System.out.println("Transferencia: " + transferencia.getStatus());

        } catch (InsufficientFundsException | InvalidPaymentException error) {

            System.out.println("Error: " + error.getMessage());
        }



        // CASO 4 - POLIMORFISMO

        System.out.println("\n--- CASO 4: POLIMORFISMO ---");

        // El administrador trabaja con objetos Payment
        manager.showPayment();

        System.out.println("Total de pagos aprobados: $" + manager.getTotal());


        // CASO 5 - REEMBOLSO

        System.out.println("\n--- CASO 5: REEMBOLSO ---");

        // PayPal implementa Refundable
        Refundable reembolsable = (Refundable) paypal;

        reembolsable.refund();

        System.out.println("Estado despues del reembolso: " + paypal.getStatus());

        System.out.println("Total aprobado despues del reembolso: $" + manager.getTotal());



        // CASO 6 - BUSQUEDA POR ID


        System.out.println("\n--- CASO 6: BUSQUEDA ---");

        // Buscar ID existente
        int idBuscado = tarjeta.getID();

        Payment encontrado = manager.findById(idBuscado);

        if (encontrado != null) {

            System.out.println("Pago encontrado:");
            System.out.println(encontrado);

        } else {

            System.out.println("Pago no encontrado");
        }

        // Buscar ID inexistente
        Payment noEncontrado = manager.findById(999);

        if (noEncontrado == null) {

            System.out.println("No existe ningun pago con ID 999");

        } else {

            System.out.println(noEncontrado);
        }



        // RESUMEN FINAL


        System.out.println("\n--- RESUMEN FINAL ---");

        manager.showPayment();

        System.out.println("Monto total aprobado: $" + manager.getTotal());

        System.out.println("\nFin de las pruebas");

    } // main

} // PaymentApp