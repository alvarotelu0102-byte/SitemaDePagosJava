# Sistema de Procesamiento de Pagos en Java
## Integrantes del equipo 4 :
### Víctor Manuel Lázaro Bravo
### Mara Lizette Gonzalez Gandara
### Luis Fernando Sanchez Hernandez
### José Álvaro Terrones Luna 

## 🎯 Objetivo del Proyecto

Desarrollar un robusto **sistema de procesamiento de pagos** utilizando Java y aplicando de forma estricta los conceptos fundamentales de la Programación Orientada a Objetos (POO). 

El sistema permite registrar diferentes métodos de pago, procesar transacciones bajo reglas de negocio específicas, gestionar reembolsos, administrar colecciones de datos de manera polimórfica y manejar situaciones de error mediante excepciones personalizadas.

---

## 🧭 1. Contexto

Una tienda en línea requiere un sistema automatizado para procesar múltiples formas de pago de manera eficiente y escalable. Actualmente, el negocio acepta tres métodos principales:

- **Tarjeta de Crédito** (`CreditCardPayment`)
- **PayPal** (`PayPalPayment`)
- **Transferencia Bancaria** (`BankTransferPayment`)

Aunque todos estos métodos comparten atributos y comportamientos comunes (como un identificador único, un monto y un estado), cada uno procesa las transacciones y valida sus reglas internas de manera independiente. Además, el sistema centraliza el registro de todas las transacciones para su consulta y administración.

---

## 📋 2. Requisitos Funcionales

El sistema maneja tres tipos de métodos de pago con sus respectivas particularidades:

1. **`CreditCardPayment`**
   - Número de tarjeta
   - Nombre del titular
   - Límite de crédito disponible
2. **`PayPalPayment`**
   - Correo electrónico asociado
   - Saldo disponible
3. **`BankTransferPayment`**
   - Número de cuenta
   - Nombre del banco
   - Saldo disponible

*(Nota: Los datos bancarios y montos utilizados son simulados con fines exclusivamente académicos y de prueba).*

---

## 🏛️ 3. Clase Abstracta `Payment`

Se implementó una clase abstracta base que representa el concepto general de un pago:

- **Atributos comunes:** `id`, `monto` y `estado`.
- **Enumerador de Estado (`PaymentStatus`):** Controla el ciclo de vida de la transacción mediante los estados:
  - `PENDING` (Pendiente)
  - `APPROVED` (Aprobado)
  - `REJECTED` (Rechazado)
  - `REFUNDED` (Reembolsado)
- **Método abstracto:** `processPayment()`, el cual obliga a cada clase hija a implementar su propia lógica de validación y procesamiento.

---

## 🔌 4. Interfaz `Refundable`

Dado que no todos los métodos de pago admiten devoluciones de dinero bajo las mismas condiciones, se diseñó la interfaz **`Refundable`**:
- Define el contrato con el método **`refund()`**.
- Únicamente los métodos de pago elegibles implementan esta interfaz para permitir la reversión de fondos y la actualización del estado de la transacción.

---

## ⚙️ 5. Reglas de Negocio en el Procesamiento de Pagos

Cada método valida condiciones particulares antes de aprobar una transacción:

- **Tarjeta de Crédito:** El pago solo se aprueba si el monto solicitado no supera el límite de crédito disponible. De lo contrario, se rechaza y se lanza una excepción.
- **PayPal:** Valida que exista el saldo suficiente en la cuenta digital del usuario.
- **Transferencia Bancaria:** Verifica que los datos bancarios estén completos (número de cuenta y banco válidos) y que el saldo sea suficiente.

---

## 🗂️ 6. Colección de Pagos (`PaymentManager`)

La administración de las transacciones se centraliza en la clase **`PaymentManager`**, la cual utiliza internamente un `ArrayList<Payment>`. 

Gracias al uso de **polimorfismo**, `PaymentManager` opera exclusivamente con referencias de tipo `Payment`, ignorando las implementaciones concretas. Sus funciones principales son:
- Registrar un nuevo pago.
- Mostrar el listado completo de pagos registrados.
- Buscar un pago específico mediante su ID.
- Calcular y mostrar el total de los pagos procesados.

---

## ⚠️ 7. Manejo de Excepciones

El sistema previene fallos inesperados y asegura la integridad de los datos implementando excepciones personalizadas:
- **`InsufficientFundsException`:** Se lanza cuando el saldo o límite disponible es menor al monto de la transacción.
- **`InvalidPaymentException`:** Se dispara ante datos de entrada erróneos, como información bancaria incompleta o montos/límites inválidos.

---

## 📁 8. Estructura del Proyecto

El código fuente se encuentra organizado bajo una arquitectura limpia en paquetes:

```text
src
└── com.payments
    ├── entities
    │   ├── Payment.java (Clase abstracta)
    │   ├── CreditCardPayment.java
    │   ├── PayPalPayment.java
    │   ├── BankTransferPayment.java
    │   └── PaymentManager.java
    |   └── PaymentStatus.jav
    │
    ├── interfaces
    │   └── Refundable.java
    │
    ├── exceptions
    │   ├── InsufficientFundsException.java
    │   └── InvalidPaymentException.java
    │
    └── PaymentsApp.java (Punto de entrada / Main)
