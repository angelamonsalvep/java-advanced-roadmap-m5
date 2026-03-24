# Soporte Temático - Semana 1: Arquitectura y Fundamentos de Java 📚

Este documento es tu biblia para esta semana. Aquí encontrarás la base teórica para los retos de arquitectura, tipos de datos y lógica.

---

## 1. El Motor de Java: JDK vs JVM
- **JDK (Java Development Kit)**: Tu caja de herramientas. Contiene el compilador `javac`. Sin él, no puedes crear software.
- **JVM (Java Virtual Machine)**: El motor que ejecuta el código. Lee el **Bytecode** (.class) y lo traduce a instrucciones que tu procesador entiende.
- **WORA (Write Once, Run Anywhere)**: La JVM permite que el mismo archivo `.class` corra en Windows, Mac o Linux sin cambios.

---

## 2. Los 8 Tipos Primitivos (Los Ladrillos)
Java es de tipado fuerte. Debes conocer tus herramientas básicas:

| Tipo | Tamaño | Valor por defecto | Uso / Nota |
| :--- | :--- | :--- | :--- |
| `byte` | 8 bits | 0 | Valores muy pequeños (-128 a 127). |
| `short` | 16 bits | 0 | Valores pequeños. |
| `int` | 32 bits | 0 | El estándar para números enteros. |
| `long` | 64 bits | 0L | Números grandes. **Requiere sufijo L**. |
| `float` | 32 bits | 0.0f | Decimales simples. **Requiere sufijo f**. |
| `double` | 64 bits | 0.0d | Decimales de alta precisión (estándar). |
| `char` | 16 bits | '\u0000' | Un solo carácter (Unicode). |
| `boolean`| 1 bit | false | `true` o `false`. |

---

## 3. Jerarquía de Operadores (El Orden del Caos)
Java no evalúa de izquierda a derecha sin reglas. Sigue este orden (de mayor a menor prioridad):
1.  **Paréntesis** `()`: Siempre se evalúa primero.
2.  **Incremento/Decremento** `++`, `--`, y **Negación** `!`.
3.  **Multiplicación/División/Módulo** `*`, `/`, `%`.
4.  **Suma/Resta** `+`, `-`.
5.  **Relacionales** `<`, `>`, `<=`, `>=`.
6.  **Igualdad** `==`, `!=`.
7.  **Lógica AND** `&&`.
8.  **Lógica OR** `||`.
9.  **Asignación** `=`, `+=`, `-=`, etc.

---

## 4. Evolución Modern (Java 17/21)
- **Records**: Estructuras de datos inmutables que eliminan el código repetitivo (getters, setters, toString). 
- **Text Blocks (`""" ... """`)**: Permiten escribir strings de múltiples líneas de forma legible.
- **Helpful NPEs**: En Java 17+, los errores de nulos te dicen exactamente *qué* variable era nula, facilitando el diagnóstico.

---

## 5. Gestión de Memoria: Stack vs Heap
- **Stack (Pila)**: Donde viven las variables locales y las llamadas a métodos. Es rápido y pequeño.
- **Heap (Montón)**: Donde viven los **Objetos** (instancias). Es grande y gestionado por el **Garbage Collector (GC)**.
- **Garbage Collector**: Un proceso automático de la JVM que libera la memoria de objetos que ya no se usan. Java 21 tiene un GC altamente optimizado para cargas masivas.

---
*Usa esta guía para validar tu lógica en el simulador de reglas y en el laboratorio de nulos.*
