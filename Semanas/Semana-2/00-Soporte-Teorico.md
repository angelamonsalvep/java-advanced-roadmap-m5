# Soporte Temático - Semana 2: Control de Flujo y Evolución 📚

Este documento explica las herramientas de lógica que daremos al sistema esta semana.

## 1. Switch Expression (Java 17/21)
La evolución del `switch` clásico. Usa `->` en lugar de `case : break;`.
- **Ventaja**: No tiene el riesgo de "fall-through" y puede devolver un valor directamente.

## 2. Inferencia de Tipos (`var`)
Introducido en Java 11. Permite que el compilador deduzca el tipo de la variable local.
- **Regla de oro**: Úsalo solo cuando el tipo sea obvio (ej: `var lista = new ArrayList<String>();`).

## 3. Matrices (`double[][]`)
Estructuras de datos multidimensionales para almacenar datos complejos (como notas por trimestre).

## 4. Excepciones (`try-catch`)
La forma en que Java gestiona errores inesperados sin que el programa se cierre.

## 5. Casting
Cómo convertir tipos de datos (ej: de `double` a `int`). Cuidado con la pérdida de precisión al hacer casting explícito.
