# 🚀 Ejercicio Integrado: Micro-Payroll System

Este ejercicio está diseñado para aplicar los conceptos aprendidos durante la **Semana 1** (Día 2 y Día 3), incluyendo Records, Text Blocks, Operadores, Gestión de Memoria y Compilación Manual.

## 1. Estructura del Proyecto

Antes de empezar, organiza tus carpetas de la siguiente manera:

```text
micro-payroll/
├── src/
│   ├── Main.java
│   └── com/
│       └── startup/
│           ├── models/
│           │   ├── Empleado.java
│           │   └── Empresa.java
│           └── utils/
│               └── CalculadoraNomina.java
└── bin/
```

## 2. Modelado de Datos (Día 2)

Utilizamos **Records** para definir datos inmutables de forma concisa.

### `Empleado.java`
```java
package com.startup.models;

// Aplicamos Records (Día 2) e Inmutabilidad
public record Empleado(String nombre, int id, double salarioBase, boolean esRemoto) {
    // El record ya incluye equals, hashCode y toString automáticamente
}
```

### `Empresa.java`
```java
package com.startup.models;

public record Empresa(String nombre, int sedes) {}
```

## 3. Lógica de Negocio y Memoria (Día 3 + Día 2)

### `CalculadoraNomina.java`
```java
package com.startup.utils;

public class CalculadoraNomina {
    
    public static double calcularNeto(double salario, double bono) {
        // Reto Día 3: Jerarquía de operadores
        // (Salario + bono con 10% extra) - (5% de retención sobre el salario)
        return (salario + bono * 1.10) - (salario * 0.05);
    }

    public static void demostrarOverflow() {
        // Reto Día 2: Análisis de desbordamiento (Stack Memory)
        byte nivelSeguridad = 127;
        nivelSeguridad = (byte) (nivelSeguridad + 1);
        System.out.println("⚠️ Alerta de Sistema - Nivel de Seguridad (Overflow): " + nivelSeguridad);
        // Explicación: 127 + 1 en un byte de 8 bits resulta en -128 por el complemento a dos.
    }
}
```

## 4. Clase Principal y Ejecución (Día 3)

### `Main.java`
```java
import com.startup.models.*;
import com.startup.utils.CalculadoraNomina;

public class Main {
    public static void main(String[] args) {
        // 1. Representación Visual con Text Block (Día 2)
        String banner = """
            ******************************************
            * CORPORATE TALENT HUB v1.0              *
            * Gestionando el futuro del código       *
            ******************************************
            """;
        System.out.println(banner);

        // 2. Instanciación y Lógica de Cortocircuito (Día 3)
        Empleado dev = new Empleado("Coder Java", 101, 3000.0, true);
        
        // Ejemplo de Cortocircuito: Si no es remoto, ni siquiera evalúa el ID
        if (dev.esRemoto() && dev.id() % 2 == 0) {
            System.out.println("Empleado " + dev.nombre() + " asignado a Sede Virtual Par.");
        }

        // 3. Cálculo de Nómina
        double neto = CalculadoraNomina.calcularNeto(dev.salarioBase(), 500);
        System.out.println("Salario Neto Final: $" + neto);

        // 4. Prueba de Memoria
        CalculadoraNomina.demostrarOverflow();
    }
}
```

## 🛠️ Guía para el Live Coding (Paso a Paso)

1.  **Creación de Carpetas:** Explica que los paquetes en Java corresponden a la estructura de directorios física.
2.  **Compilación Manual:** Evita el botón "Play". Usa la terminal para mostrar cómo funciona el Classpath:
    ```bash
    # Desde la carpeta raíz del proyecto (micro-payroll)
    mkdir bin
    javac -d bin src/com/startup/models/*.java src/com/startup/utils/*.java src/Main.java
    ```
3.  **Ejecución:**
    ```bash
    java -cp bin Main
    ```

### El Momento "¡Ajá!":
*   **Inmutabilidad:** Intenta cambiar `dev.salarioBase = 5000;` en el `Main`. Fallará porque los Records son inmutables.
*   **Stack vs Heap:** Muestra cómo el `int id` vive en el stack, mientras que `String nombre` vive en el Heap, y el record guarda la referencia.
*   **Overflow:** Explica por qué el nivel de seguridad pasó de 127 a -128 al sumar 1.
