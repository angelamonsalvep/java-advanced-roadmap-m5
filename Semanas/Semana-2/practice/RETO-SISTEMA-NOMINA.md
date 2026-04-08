# 🛡️ Reto Maven: Robustez en el Sistema de Nómina

## Contexto
La empresa **"TechSolutions"** está migrando sus herramientas internas a **Java 17/21** utilizando **Maven**. Tu misión es crear un módulo de diagnóstico que procese la elegibilidad de bonos, asegurando que el sistema sea resistente a errores de entrada y fácil de depurar.

---

## 📋 Instrucciones del Proyecto

### 1. Estructura del Proyecto
*   Crea un proyecto Maven con el `artifactId`: `sistema-nomina-robusto`.
*   Asegúrate de que el `pom.xml` esté configurado para usar **Java 17 o superior** (propiedades `maven.compiler.source` y `target`).

### 2. Captura Segura (Capa de Servicio)
*   Crea una clase principal dentro del paquete `com.nomina.main`.
*   Implementa la lectura de:
    *   **ID del Empleado** (`int`)
    *   **Promedio de Desempeño** (`double`)
*   **Requisito:** Envuelve la lógica en un bloque `try-catch`. Si el usuario ingresa texto en lugar de números, captura la `InputMismatchException` y muestra un mensaje de diagnóstico que incluya el nombre de la excepción.

### 3. Lógica con Operador Ternario
*   Define una variable `String resultado`.
*   Si el desempeño es **>= 8.5**, el valor será `"ELEGIBLE PARA BONO"`.
*   De lo contrario, será `"CONTINUAR EVALUACIÓN"`.

### 4. Gestión de Recursos (Try-with-resources)
*   **Upgrade Tecnológico:** En lugar de usar `finally` para cerrar el `Scanner`, implementa el **Try-with-resources** (estándar en proyectos modernos), que cierra automáticamente el recurso al terminar el bloque.

### 5. Investigación y Diagnóstico (Java Moderno)
*   Dentro de la carpeta `src/main/resources`, crea un archivo llamado `diagnostico.txt`.
*   Escribe en él una breve explicación de por qué las **Helpful NullPointerExceptions (JEP 358)** de Java 17 son superiores a los errores de las versiones "Legacy" (Java 8) al momento de debugear en producción.

---

## 🏆 Bonus Challenge: "La Auditoría Invisible"
Para ver el poder de **Java 17** en acción:
1.  Crea una pequeña cadena de objetos (ej: `Empleado -> Contrato`).
2.  Intenta acceder a una propiedad del `Contrato` sin haberlo inicializado (que sea `null`).
3.  Imprime el mensaje de la excepción (`e.getMessage()`) en un bloque `catch` y observa cómo Java te dice exactamente qué método devolvió `null`.
