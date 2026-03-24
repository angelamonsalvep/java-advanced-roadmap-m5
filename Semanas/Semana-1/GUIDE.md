# Semana 1: Anatomía de Java y Modelado Core 🏗️

Esta semana descubrirás qué pasa "bajo el capó" de Java mientras construyes las piezas fundamentales del "Corporate Talent Hub".

## Objetivos Diarios

### 🗓️ Día 1: La Ingeniería Forense (JDK vs JVM)
- **Meta**: Diferenciar el compilador (`javac`) del motor de ejecución (`java`).
- **Tarea**: 
    1. Escribe un `HolaMundo.java` en bloc de notas.
    2. Compila con `javac` (JDK) y observa el archivo `.class`. 
    3. **El Experimento**: Borra el `.java` y corre solo el `.class` con `java` (JVM).
- **IA Tip**: `gemini "Dime el comando javap -c para ver el Bytecode y explícame por qué el Bytecode es el secreto de la portabilidad de Java"`

### 🗓️ Día 2: Los Ladrillos del Sistema (Tipos Primitivos y Records)
- **Meta**: Dominar el modelado de datos usando los **8 tipos primitivos** y la evolución moderna.
- **Tarea**: 
    1. Crea la clase `Empleado` declarando `byte`, `short`, `int`, `long` (L), `float` (f), `double`, `char` y `boolean`.
    2. Usa **Text Blocks** (`"""`) para un encabezado del sistema.
    3. **Evolución**: Crea un `record EmpresaRecord` para los datos de la compañía.
- **IA Tip**: `gemini "Explícame por qué el long lleva una L y el float una f al final. ¿Qué pasa si los omito?"`

### 🗓️ Día 3: El Motor de Reglas (Jerarquía y Classpath)
- **Meta**: Implementar lógica compleja gestionando múltiples clases a mano.
- **Tarea**: 
    1. Crea un método `calcularSalarioFinal()` aplicando el orden de evaluación: `(salarioBase + (bono * 1.10)) - (salario * 0.05)`.
    2. Usa el operador módulo (`%`) para determinar bonos según el ID.
    3. **El Reto**: Compila dos clases en carpetas separadas usando el flag `-cp` (Classpath) sin ayuda de IDEs.
- **IA Tip**: `gemini "Explícame la jerarquía de operadores en esta expresión: a + b * c && d || !e"`

### 🗓️ Día 4: Estructura Profesional y Diagnóstico (Maven y Nulos)
- **Meta**: Pasar del caos manual al orden industrial (Maven) y validar la robustez.
- **Tarea**: 
    1. Migra tu proyecto a Maven con `mvn archetype:generate`.
    2. **Laboratorio de Nulos**: Provoca un `NullPointerException` intencional para ver el diagnóstico detallado (Helpful NPEs) de Java 17+.
    3. Analiza la diferencia entre **Stack vs Heap** en la memoria al crear objetos.
- **IA Tip**: `gemini "Explícame cómo los Helpful NullPointerExceptions de Java 17 nos ayudan a encontrar errores más rápido que en Java 11"`

---
¡Felicidades! Al terminar el Día 4, habrás pasado de la terminal pura a un entorno profesional de ingeniería.
