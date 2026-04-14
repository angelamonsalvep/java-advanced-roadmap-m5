# 🚀 Live Coding: Controlando el Legado (Sealed Persona Edition)

## 🎯 El Escenario
Estamos modernizando un sistema de gestión de personal. Históricamente, cualquier clase podía extender nuestra clase base `Persona`, lo que ha llevado a jerarquías incontrolables y comportamientos inesperados. Queremos recuperar el control y definir exactamente quién puede ser considerado una "Persona" en nuestro sistema, ¡y el compilador debe ayudarnos a enforces esto!

## 🛠️ El Reto en Vivo (30 min)

### Paso 1: La Herencia Abierta (5 min)
*   Crea una clase abstracta `Persona` con atributos básicos (ej. `String nombre`, `int edad`) y un método abstracto `void trabajar()`.
*   Demuestra cómo una clase `Estudiante` y una clase `Profesor` pueden heredar e implementar `Persona` sin restricciones, mostrando la flexibilidad (y la vulnerabilidad) de la herencia tradicional.

### Paso 2: Sellando el Dominio (10 min)
*   Refactoriza la clase `Persona` para que sea una **`sealed class`**.
*   Utiliza la cláusula `permits` para autorizar explícitamente solo a `Empleado` y `ConsultorExterno` como sus subclases directas.
*   **Provoca el error:** Intenta que `Estudiante` o `Profesor` sigan heredando de `Persona` para que tus coders vean el error de compilación `is not allowed in the sealed hierarchy`.

### Paso 3: Definiendo el Futuro de las Subclases (10 min)
*   Implementa `Empleado` como una clase `final` (No queremos que `Empleado` tenga más jerarquías).
*   Implementa `ConsultorExterno` como una `sealed class` para permitir una subclase específica `ConsultorInternacional` dentro de su jerarquía.
*   Crea una subclase `DesarrolladorFreelance` que herede de `ConsultorExterno`, pero márcala como `non-sealed` para permitir futuras extensiones no previstas inicialmente por el equipo de consultores.

### Paso 4: El "Checkmate" del Compilador (5 min)
*   Crea un método `void procesarPersona(Persona p)` que use un `switch expression` para manejar los diferentes tipos de `Persona` autorizados.
*   Observa cómo Java nos obliga a cubrir todos los casos definidos en la jerarquía `sealed` de `Persona` (es decir, `Empleado` y `ConsultorExterno`) sin necesidad de un `default`.
*   Explica cómo el compilador garantiza la exhaustividad y te forzará a actualizar este `switch` si añades nuevos `permits` a `Persona` en el futuro.

---

## 💡 Tips para tu sesión:

*   **Visualiza el impacto:** Empieza con el error de herencia abierta para que los coders sientan la necesidad de control.
*   **El `switch` es clave:** Enfatiza cómo el `switch expression` elimina el `default` y ofrece seguridad en tiempo de compilación. Este es el `selling point` de las `Sealed Classes`.
*   **Discute `final`, `sealed` y `non-sealed`:** ¿Cuándo usar cada uno? ¿Qué control otorgan? La clave de `non-sealed` es delegar libertad.
*   **Pregunta clave:** "¿Qué nivel de control perdemos/ganamos al usar `final`, `sealed` o `non-sealed` en cada parte de la jerarquía?"

¡Prepárate para una sesión interactiva y reveladora sobre el poder de la herencia sellada en Java! ¡A codear! 💪
