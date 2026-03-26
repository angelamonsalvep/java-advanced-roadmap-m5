# 🕰️ Guía: El Mundo Legacy vs. Java Moderno

Esta guía muestra cómo se programaba el mismo sistema antes de las mejoras de **Java 17+**, para que los estudiantes aprecien la evolución del lenguaje (Records, Text Blocks, Inmutabilidad).

## 1. El Desafío del "Boilerplate" (Código Repetitivo)

En versiones antiguas (Java 8 y anteriores), no existían los **Records**. Teníamos que escribir todo manualmente.

### `EmpleadoLegacy.java` (Versión Antigua)
Compara estas **30+ líneas** de código con las **1 sola línea** del Record moderno:

```java
package com.startup.models;

import java.util.Objects;

public class EmpleadoLegacy {
    private final String nombre;
    private final int id;
    private final double salarioBase;
    private final boolean esRemoto;

    // Constructor Manual
    public EmpleadoLegacy(String nombre, int id, double salarioBase, boolean esRemoto) {
        this.nombre = nombre;
        this.id = id;
        this.salarioBase = salarioBase;
        this.esRemoto = esRemoto;
    }

    // Getters Manuales (No hay .nombre(), es .getNombre())
    public String getNombre() { return nombre; }
    public int getId() { return id; }
    public double getSalarioBase() { return salarioBase; }
    public boolean isEsRemoto() { return esRemoto; }

    // toString Manual (¡Obligatorio o verás algo como Empleado@1a2b3c!)
    @Override
    public String toString() {
        return "EmpleadoLegacy{nombre='" + nombre + "', id=" + id + "}";
    }

    // equals y hashCode Manuales (Si olvidas esto, las colecciones fallan)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpleadoLegacy that = (EmpleadoLegacy) o;
        return id == that.id && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, id);
    }
}
```

## 2. Concatenación vs. Text Blocks (Día 2)

Antes de Java 15, no existían las comillas triples (`"""`). Los banners visuales eran un dolor de cabeza:

### `MainLegacy.java`
```java
public class MainLegacy {
    public static void main(String[] args) {
        // Antes: Concatenación manual con \n
        String banner = "******************************************\n" +
                        "* CORPORATE TALENT HUB v0.1 (LEGACY)     *\n" +
                        "* El código era más largo entonces...    *\n" +
                        "******************************************";
        System.out.println(banner);

        EmpleadoLegacy emp = new EmpleadoLegacy("Dev Antiguo", 99, 2000.0, false);
        
        // Uso de Getters (Verbosidad)
        System.out.println("Empleado: " + emp.getNombre() + " (ID: " + emp.getId() + ")");
    }
}
```

## 3. Tabla Comparativa: Legacy vs. Moderno

| Característica | Legacy (Java 8) | Moderno (Java 17+) |
| :--- | :--- | :--- |
| **Modelos de Datos** | Clases con Atributos, Getters, Setters, toString, equals. | **Records** (Inmutabilidad por defecto, 1 línea). |
| **Cadenas Largas** | Concatenación con `+` y `\n`. | **Text Blocks** (`"""`). |
| **Memoria** | Más objetos intermedios por concatenación. | Optimización interna de strings y records. |
| **Legibilidad** | El "ruido" de código oculta la lógica. | Código limpio y directo a la intención. |

## 🛠️ Ejercicio para el Live Coding: "El Refactoring"

1.  **Muestra el Legacy:** Abre el archivo `EmpleadoLegacy.java` (el que tiene Getters/Setters).
2.  **El Reto:** Pide a un estudiante que añada un nuevo campo `email`. Tendrá que actualizar el Constructor, el Getter, el `toString`, el `equals` y el `hashCode`.
3.  **Muestra el Record:** Haz lo mismo en el Record `Empleado.java`. Solo añades `String email` en el paréntesis y **¡listo!**.
4.  **Conclusión:** Pregúntales: *"Si tienes 50 tablas en una base de datos, ¿prefieres escribir 50 clases legacy o 50 records?"*
