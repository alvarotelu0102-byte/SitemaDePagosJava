# Ejercicio: Sistema de Pagos
## Integrantes del equipo 4 :
### Víctor Manuel Lázaro Bravo
### Mara Lizette Gonzalez Gandara
### Luis Fernando Sanchez Hernandez
### José Álvaro Terrones Luna 


## Objetivo

Desarrollar en equipo un pequeño **sistema de procesamiento de pagos** utilizando Java y los conceptos de Programación Orientada a Objetos estudiados en clase.

El sistema deberá permitir registrar diferentes métodos de pago, procesar transacciones y manejar situaciones en las que un pago no pueda realizarse.

Durante el desarrollo deberán aplicar:

- Encapsulación
- Abstracción
- Herencia
- Polimorfismo
- Interfaces
- Colecciones
- Manejo de excepciones
- Organización del código en paquetes
- Trabajo colaborativo con Git y GitHub

> **Importante:** El objetivo no es construir un sistema bancario real. Se busca representar, mediante código, cómo diferentes tipos de pago pueden compartir características y comportamientos, pero también tener reglas particulares.

---

# 1. Contexto

Una tienda en línea necesita implementar un sistema que permita procesar diferentes formas de pago.

Actualmente la tienda acepta:

- Tarjeta de crédito
- PayPal
- Transferencia bancaria

Todos estos métodos tienen información y comportamientos comunes, pero cada uno procesa el pago de una manera diferente.

Además, el sistema debe registrar las transacciones realizadas y permitir consultar su información.

Por ejemplo:

```text
Cliente realiza una compra de $850.00
             ↓
Selecciona método de pago
             ↓
      Tarjeta de crédito
             ↓
       Procesar pago
             ↓
       ¿Pago exitoso?
          /       \
        Sí         No
        ↓           ↓
 Registrar       Manejar
 transacción     excepción
```

---

# 2. Requisitos funcionales

El sistema deberá cumplir con los siguientes requisitos.

## RF01 — Métodos de pago

El sistema deberá manejar al menos tres métodos:

1. `CreditCardPayment`
2. `PayPalPayment`
3. `BankTransferPayment`

Cada uno deberá tener información específica.

Por ejemplo:

### Tarjeta de crédito

- Número de tarjeta
- Nombre del titular
- Límite disponible

### PayPal

- Correo electrónico
- Saldo disponible

### Transferencia bancaria

- Número de cuenta
- Banco
- Saldo disponible

No es necesario utilizar información bancaria real.

**Utilicen datos ficticios exclusivamente para el ejercicio.**

---

# 3. Clase abstracta `Payment`

Crear una clase abstracta que represente el concepto general de un pago.

Deberá contener información común, por ejemplo:

- ID
- Monto
- Estado del pago

El estado puede representarse mediante un `String` o, preferentemente, mediante un `enum`.

Algunos estados posibles:

```text
PENDING
APPROVED
REJECTED
```

La clase deberá definir el comportamiento que todos los métodos de pago deben implementar.

Por ejemplo:

```text
processPayment()
```

Cada método de pago deberá implementar este comportamiento de manera diferente.

---

# 4. Interfaz `Refundable`

No todos los métodos de pago tienen necesariamente las mismas capacidades.

Crear una interfaz:

```text
Refundable
```

que represente la capacidad de realizar un reembolso.

Deberá definir un método:

```text
refund()
```

No todos los métodos de pago están obligados a implementar esta interfaz.

El equipo deberá decidir cuáles métodos permiten reembolsos y justificar su decisión durante la presentación.

---

# 5. Procesamiento de pagos

Cada método de pago deberá tener sus propias reglas.

Por ejemplo:

### Tarjeta de crédito

El pago solamente podrá aprobarse si el monto no supera el límite disponible.

```text
Límite disponible: $5,000
Pago: $1,000
Resultado: aprobado
```

Pero:

```text
Límite disponible: $500
Pago: $1,000
Resultado: rechazado
```

### PayPal

El pago solamente podrá aprobarse si existe suficiente saldo.

### Transferencia bancaria

Deberá validar que exista saldo suficiente y que los datos necesarios de la cuenta sean válidos.

> Las reglas anteriores son ejemplos. El equipo puede establecer reglas adicionales siempre que estén claramente documentadas.

---

# 6. Colección de pagos

Crear una clase responsable de administrar los pagos realizados.

Por ejemplo:

```text
PaymentManager
```

Esta clase deberá almacenar los pagos utilizando una colección apropiada.

Se recomienda:

```text
ArrayList<Payment>
```

El administrador deberá permitir como mínimo:

- Registrar un pago.
- Mostrar los pagos registrados.
- Buscar un pago mediante su ID.
- Mostrar el total de pagos procesados.

El sistema deberá aprovechar el polimorfismo.

`PaymentManager` deberá trabajar con referencias de tipo:

```text
Payment
```

y no depender directamente de:

```text
CreditCardPayment
PayPalPayment
BankTransferPayment
```

---

# 7. Excepciones

El sistema deberá manejar situaciones que impidan realizar una operación.

Crear al menos **dos excepciones personalizadas**.

Algunos ejemplos:

```text
InsufficientFundsException
InvalidPaymentException
```

El equipo deberá determinar cuándo corresponde utilizar cada una.

Por ejemplo:

```text
Saldo disponible: $500
Monto del pago: $1,000
        ↓
InsufficientFundsException
```

Las excepciones deberán ser manejadas adecuadamente para evitar que el programa termine inesperadamente.

---

# 8. Requisitos adicionales

El sistema deberá permitir ejecutar varias operaciones desde `main`.

No es necesario crear un menú interactivo complejo.

El programa deberá demostrar como mínimo:

1. Crear diferentes métodos de pago.
2. Procesar pagos exitosos.
3. Intentar procesar un pago que no pueda realizarse.
4. Manejar la excepción correspondiente.
5. Registrar los pagos.
6. Mostrar los pagos registrados.
7. Buscar un pago mediante su ID.
8. Realizar al menos un reembolso sobre un método que implemente `Refundable`.

---

# 9. Estructura sugerida

Utilicen una estructura similar a:

```text
src
└── com.payments
    ├── entities
    │   ├── Payment
    │   ├── CreditCardPayment
    │   ├── PayPalPayment
    │   ├── BankTransferPayment
    │   └── PaymentManager
    │
    ├── interfaces
    │   └── Refundable
    │
    ├── exceptions
    │   ├── InsufficientFundsException
    │   └── InvalidPaymentException
    │
    └── PaymentsApp
```

La estructura anterior es una **propuesta**. Si el equipo considera que otra organización es más adecuada, puede utilizarla siempre que pueda justificar su decisión.

---

# 10. División del trabajo

El equipo deberá distribuir las responsabilidades antes de comenzar a programar.

Para equipos de 3 personas:

### Integrante 1 — Modelo de pagos

Responsable de:

- `Payment`
- `CreditCardPayment`
- `PayPalPayment`

### Integrante 2 — Métodos de pago y excepciones

Responsable de:

- `BankTransferPayment`
- `Refundable`
- Excepciones personalizadas

### Integrante 3 — Administración e integración

Responsable de:

- `PaymentManager`
- Integración de las clases
- Pruebas generales
- `PaymentsApp`

---

### Para equipos de 4 personas

Pueden distribuirse de la siguiente manera:

### Integrante 1

- `Payment`
- `CreditCardPayment`

### Integrante 2

- `PayPalPayment`
- `BankTransferPayment`

### Integrante 3

- `Refundable`
- Excepciones
- Validaciones

### Integrante 4

- `PaymentManager`
- `PaymentsApp`
- Pruebas de integración

> **Importante:** La división de responsabilidades no significa que cada integrante trabaje exclusivamente en su código. Todos deben comprender el funcionamiento completo del sistema.

---

# 11. Estrategia de trabajo con Git y GitHub

Para evitar conflictos innecesarios, utilicen una estrategia sencilla.

## Paso 1 — Crear el repositorio

Un integrante deberá crear el repositorio en GitHub y agregar al resto del equipo como colaboradores.

Solamente deberán existir **dos ramas principales**:

```text
main
develop
```

`main` deberá contener únicamente versiones funcionales del proyecto.

---

## Paso 2 — Cada integrante trabajará en su propia rama

No trabajen directamente sobre `main`.

Cada integrante deberá crear una rama a partir de `develop`.

Ejemplos:

```text
feature/payment-model
feature/payment-methods
feature/exceptions
feature/payment-manager
```

La rama deberá representar claramente la funcionalidad que están desarrollando.

---

## Paso 3 — Commits pequeños

Eviten realizar un único commit con todo el trabajo.

Es preferible:

```text
feat: create abstract Payment class
feat: add credit card payment
feat: implement payment validation
feat: add insufficient funds exception
```

que:

```text
final project
```

Los commits deberán describir **qué cambió**, no quién lo hizo.

---

## Paso 4 — Actualizar la rama antes de subir cambios

Antes de solicitar integrar una funcionalidad:

```text
develop
   ↓
actualizar rama
   ↓
realizar cambios
   ↓
commit
   ↓
push
   ↓
Pull Request
```

No realicen cambios importantes directamente en `develop`.

---

# 12. Pull Requests

Cuando una funcionalidad esté terminada:

1. Subir la rama a GitHub.
2. Crear un Pull Request hacia `develop`.
3. Otro integrante deberá revisar el código.
4. Resolver los comentarios.
5. Integrar el Pull Request.

**No se recomienda que una persona revise y apruebe su propio código.**

La revisión debe comprobar:

- ¿Compila?
- ¿Cumple los requisitos?
- ¿El código es comprensible?
- ¿Se respetan las responsabilidades de las clases?
- ¿Se utilizan correctamente las clases abstractas?
- ¿Se utilizan correctamente las interfaces?
- ¿Se manejan las excepciones?
- ¿Se introdujeron cambios innecesarios?

---

# 13. Regla importante para evitar conflictos

Antes de comenzar a modificar un archivo que otro integrante está utilizando:

> **Comuníquense primero.**

Eviten que dos personas trabajen simultáneamente sobre el mismo archivo cuando no sea necesario.

Por ejemplo, si una persona está trabajando en:

```text
Payment.java
```

otra persona debería evitar modificarlo al mismo tiempo.

Es preferible dividir el trabajo por archivos y responsabilidades.

---

# 14. Flujo recomendado del equipo

Utilicen el siguiente flujo:

```text
                 GitHub
                    │
                  main
                    │
                 develop
          ┌─────────┼─────────┐
          │         │         │
       feature   feature   feature
          │         │         │
          └─────────┼─────────┘
                    │
              Pull Request
                    │
                 Review
                    │
                  Merge
                    │
                 develop
                    │
             Versión estable
                    │
                  main
```

La idea es que `develop` sea la rama donde integran el trabajo del equipo y `main` represente una versión que funciona correctamente.

---

# 15. IntelliJ IDEA — ¿Qué deben subir a GitHub?

El repositorio deberá contener **solamente los archivos necesarios para reconstruir y ejecutar el proyecto**.

### Sí deben subir

Como mínimo:

```text
src/
```

y los archivos propios del proyecto que correspondan.

Si utilizan **Maven**:

```text
pom.xml
```

Si utilizan **Gradle**:

```text
build.gradle
settings.gradle
gradlew
gradlew.bat
gradle/wrapper/
```

También deben incluir:

```text
README.md
```

con:

- Nombre del proyecto.
- Integrantes.
- Descripción breve.
- Tecnologías utilizadas.
- Cómo ejecutar el proyecto.
- Funcionalidades implementadas.

---

# 16. Archivos de IntelliJ que NO deben subir

No deben subir archivos generados específicamente por IntelliJ o por el proceso de compilación.

En particular:

```text
.idea/
*.iml
out/
```

Tampoco deben subir:

```text
target/
```

si utilizan Maven, ni:

```text
build/
```

si utilizan Gradle.

Estos archivos y carpetas deben incluirse en `.gitignore`.

Por ejemplo:

```text
.idea/
*.iml
out/
target/
build/
```

> Si IntelliJ genera archivos adicionales relacionados con el entorno local, consulten antes de agregarlos al repositorio. El objetivo es que el proyecto pueda ser clonado y abierto en otro equipo sin depender de la configuración personal de uno de los integrantes.

---

# 17. Pruebas mínimas

Antes de entregar el proyecto deberán comprobar los siguientes casos.

### Caso 1 — Pago exitoso

```text
Crear método de pago
        ↓
Procesar pago
        ↓
Pago aprobado
```

### Caso 2 — Pago rechazado

Intentar realizar un pago que no pueda procesarse.

Debe producirse la excepción correspondiente y el programa deberá continuar funcionando.

### Caso 3 — Diferentes métodos

Procesar al menos:

```text
CreditCardPayment
PayPalPayment
BankTransferPayment
```

### Caso 4 — Polimorfismo

El administrador deberá trabajar con:

```text
Payment
```

y no con una clase concreta.

### Caso 5 — Reembolso

Realizar un reembolso utilizando una referencia:

```text
Refundable
```

### Caso 6 — Búsqueda

Buscar un pago existente mediante su ID.

También deberán considerar qué ocurre cuando se busca un pago que no existe.

---

# 18. Reglas del proyecto

1. No está permitido utilizar código copiado de otro equipo.
2. Pueden consultar documentación oficial, apuntes y recursos proporcionados durante el curso.
3. Pueden utilizar IA como herramienta de apoyo para:
   - comprender conceptos;
   - investigar errores;
   - proponer alternativas de diseño;
   - revisar código.
4. El equipo debe ser capaz de **explicar cualquier parte del código que haya incorporado al proyecto**.
5. No se aceptará código generado por IA que ningún integrante pueda explicar.
6. Todos los integrantes deberán participar en el repositorio.
7. Los commits deberán reflejar el trabajo realizado durante el desarrollo.
8. Antes de entregar, todos los integrantes deberán clonar el repositorio en un entorno limpio y comprobar que el proyecto funciona.

---

# 19. Entrega

El repositorio deberá contener:

```text
Proyecto
├── src/
├── README.md
├── .gitignore
└── archivo de configuración del proyecto
```

Además, deberán entregar:

### Repositorio GitHub

El enlace al repositorio deberá ser compartido con el instructor.

### Presentación breve

El equipo deberá explicar:

1. Estructura del proyecto.
2. Clase abstracta utilizada.
3. Herencia.
4. Polimorfismo.
5. Interfaz.
6. Manejo de excepciones.
7. Uso de `ArrayList`.
8. Cómo dividieron el trabajo.
9. Cómo utilizaron Git y GitHub.
10. Una decisión de diseño que hayan tomado y por qué.

---

# 20. Reto adicional — opcional

Si el equipo termina antes, pueden implementar una nueva forma de pago sin modificar el funcionamiento de `PaymentManager`.

Por ejemplo:

```text
CryptoPayment
```

o:

```text
CashPayment
```

El nuevo método deberá integrarse al sistema aprovechando la estructura existente.

El objetivo es comprobar si su diseño permite **extender el sistema sin modificar innecesariamente las clases existentes**.
