# 🚀 Ejercicio Práctico - Día 2: Sistema de Registro "Riwi Academy"

## 🎯 Objetivo
Desarrollar una aplicación de consola en Java que permita registrar participantes para un Bootcamp, aplicando los conceptos de **Captura Dinámica (Scanner)**, **Inferencia de Tipos (`var`)** y **Estructuras de Control Repetitivas (`do-while`)**.

---

## 📝 Escenario
La academia "Riwi" necesita un prototipo rápido para capturar la información de los nuevos coders. El sistema debe permitir ingresar múltiples registros hasta que el usuario decida salir, validando que los datos sean coherentes.

---

## 🛠️ Requerimientos Técnicos

1.  **Captura de Datos**:
    *   Utiliza la clase `Scanner` para recibir datos desde el teclado (`System.in`).
    *   Debes capturar al menos: **Nombre Completo**, **Edad**, **Promedio Académico (GPA)** y **Estado de Inscripción (boolean)**.

2.  **Inferencia de Tipos (`var`)**:
    *   Declara todas las variables locales del método principal y dentro del bucle utilizando `var`. 
    *   *Ejemplo: `var scanner = new Scanner(System.in);`*

3.  **Bucle de Control**:
    *   Implementa un bucle `do-while` que presente un menú al usuario:
        1. Registrar nuevo coder.
        2. Salir del sistema.

4.  **Validaciones y Lógica**:
    *   Usa estructuras `if/else` para validar:
        *   La **edad** debe ser mayor o igual a 18 años.
        *   El **promedio** debe estar entre 0.0 y 5.0.
    *   Si un dato es inválido, muestra un mensaje de error y no permitas el registro (o pide el dato de nuevo).

5.  **Manejo del Buffer**:
    *   **¡Reto Profesional!**: Asegúrate de limpiar el buffer del `Scanner` después de usar métodos como `nextInt()` o `nextDouble()` antes de leer un `String` con `nextLine()`.

---

## 📂 Instrucciones de Entrega
1. Crea una nueva clase llamada `RegistroCoders` dentro del paquete `com.riwi.practice`.
2. Implementa la lógica solicitada en el método `main`.
3. Al finalizar el registro de un coder, muestra un resumen con los datos capturados usando `System.out.printf` o concatenación limpia.

---

## 💡 Tips para el Coder
*   Recuerda que `var` solo funciona para **variables locales** con inicialización inmediata. No puedes usarlo en atributos de clase ni sin asignar un valor.
*   Si el usuario ingresa un texto donde se espera un número, el programa lanzará una excepción. Por ahora, asumiremos que el usuario ingresa el tipo de dato correcto, pero ¡valida los rangos numéricos!
*   Investiga cómo usar `scanner.nextLine()` para "consumir" el salto de línea sobrante.

---
*¡Mucho éxito, Coder! La calidad de tu código define la calidad de tu software.* 💻✨
