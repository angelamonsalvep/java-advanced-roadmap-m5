# 🏢 Reto Live Coding: Sistema de Gestión de RRHH — *TalentosCorp*

> **Modalidad:** Live Coding  
> **Nivel:** Java Intermedio — Java 17+  
> **Duración estimada:** 90 – 120 minutos  
> **Herramientas:** Java 17+, JDK estándar, `javax.swing.JOptionPane`

---

## 🌍 Contexto del Negocio

**TalentosCorp** es una empresa de tecnología que necesita modernizar su sistema interno de Recursos Humanos. El sistema anterior fue escrito hace 10 años y tiene todos los síntomas del *Legacy Code*: clases abiertas a cualquier herencia, objetos mutables sin control, casting manual por todas partes e interfaces que no pueden evolucionar sin romper todo.

Tu misión como arquitecto(a) Java es construir, desde cero, el núcleo de un sistema moderno que aplique las características más relevantes de Java 17 y buenas prácticas de diseño orientado a objetos.

El sistema deberá interactuar con el usuario a través de **`JOptionPane`** para simular una experiencia de escritorio rápida durante la demo.

---

## 🗂️ Estructura del Sistema

El sistema gestiona tres tipos de personas dentro de la empresa:

| Tipo | Descripción |
|---|---|
| `Empleado` | Personal de planta con salario base |
| `Desarrollador` | Extiende `Empleado`. Tiene un lenguaje principal |
| `ConsultorExterno` | No es empleado. Trabaja por proyecto con tarifa por hora |

---

## 📋 Requerimientos por Fases

### 🔴 Fase 1 — Arquitectura Sellada *(Día 1: Sealed Classes)*

**Objetivo:** Proteger la jerarquía de personas del sistema.

1. Crea la `sealed class Persona` que solo permita ser extendida por `Empleado` y `ConsultorExterno`.
2. `Persona` debe tener los atributos `nombre` (String) e `id` (int), con acceso `protected`.
3. `Empleado` debe ser `non-sealed` (permitirá subclases como `Desarrollador`).
4. `ConsultorExterno` debe ser `final`.
5. Intenta crear una clase `Intruso` que herede de `Persona` y observa el error del compilador. Comenta en el código qué error aparece y por qué.

```
Persona (sealed)
├── Empleado (non-sealed)
│   └── Desarrollador (final)
└── ConsultorExterno (final)
```

---

### 🟡 Fase 2 — Reportes Inmutables *(Día 2: Records)*

**Objetivo:** Generar reportes de desempeño que no puedan ser alterados.

1. Crea el `record ReporteDesempeño(int idEmpleado, String nombre, double promedio, String feedback)`.
2. Agrega un **constructor compacto** que valide:
   - `promedio` debe estar entre `0.0` y `5.0`. Si no, lanza `IllegalArgumentException`.
3. Implementa un método `generarReportes(List<Empleado> empleados)` que retorne una lista de `ReporteDesempeño`.
4. Intenta modificar el `promedio` de un reporte ya creado. Captura el error del compilador y comenta por qué esto protege la integridad del dato.

> 💡 **Pista:** El `record` genera `equals()` y `hashCode()` basados en sus componentes. ¿Qué pasa si tienes dos reportes con los mismos datos?

---

### 🟢 Fase 3 — Polimorfismo Limpio *(Día 3: Pattern Matching)*

**Objetivo:** Eliminar el casting manual para procesar distintos tipos de personas.

1. `Desarrollador` debe tener el atributo `String lenguajePrincipal` y un método `generarEntregable()` que retorne un `String` descriptivo.
2. `ConsultorExterno` debe tener `double tarifaPorHora` y un método `calcularFactura(int horas)`.
3. Implementa el método:

```java
public static String procesarPersona(Persona p) {
    // Usa Pattern Matching for instanceof — SIN casting manual
}
```

Este método debe:
- Si es `Desarrollador`: retornar su entregable más el lenguaje que usa.
- Si es `ConsultorExterno`: retornar la factura calculada para 40 horas.
- Si es `Empleado` (genérico): retornar un saludo corporativo.

4. Escribe en un comentario cómo hubieras hecho esto en Java 8 (con casting manual) y compara.

---

### 🔵 Fase 4 — Contratos Evolutivos *(Día 4: Interfaces y Default Methods)*

**Objetivo:** Diseñar un contrato de promoción que pueda evolucionar sin romper nada.

1. Crea la interfaz `Promocionable` con el método abstracto:
```java
double calcularBono();
```

2. Agrega un **método `default`** `registrarPromocion()` que imprima en consola un log con la fecha y el bono calculado. Ejemplo:
```
[2025-06-10] Promoción registrada — Bono: $2400.0
```

3. Haz que `Empleado` y `Desarrollador` implementen `Promocionable` con lógicas distintas:
   - `Empleado`: bono = 10% del salario base.
   - `Desarrollador`: bono = 15% del salario base + $500 fijo por ser tech.

4. `ConsultorExterno` **no** implementa `Promocionable` (no aplica para externos).

---

## 🖥️ Fase 5 — Menú Interactivo con `JOptionPane`

**Objetivo:** Conectar todo el sistema con una interfaz gráfica sencilla.

Implementa una clase `MenuPrincipal` con un bucle que muestre el siguiente menú usando `JOptionPane.showOptionDialog(...)`:

```
═══════════════════════════════════
     🏢  TalentosCorp — RRHH
═══════════════════════════════════
  1. Registrar Empleado
  2. Registrar Desarrollador
  3. Registrar Consultor Externo
  4. Ver todos los empleados
  5. Generar Reportes de Desempeño
  6. Calcular Bono de Promoción
  7. Procesar Persona (Pattern Matching)
  0. Salir
═══════════════════════════════════
```

### Flujos esperados por opción:

| Opción | Comportamiento esperado |
|--------|------------------------|
| **1 - Registrar Empleado** | Pide nombre y salario base con `JOptionPane.showInputDialog`. Agrega a la lista. |
| **2 - Registrar Desarrollador** | Igual que empleado + pide lenguaje principal. |
| **3 - Registrar Consultor** | Pide nombre y tarifa por hora. |
| **4 - Ver todos** | Muestra lista con `JOptionPane.showMessageDialog` usando `toString()` de cada objeto. |
| **5 - Reportes** | Genera `ReporteDesempeño` para cada empleado con un promedio aleatorio entre 1.0 y 5.0. Muestra resultados en un `JTextArea` dentro de un `JScrollPane`. |
| **6 - Bono** | Llama a `calcularBono()` y `registrarPromocion()` para cada `Promocionable`. Muestra el resumen. |
| **7 - Procesar** | Pide el ID de una persona, llama a `procesarPersona(p)` y muestra el resultado. |
| **0 - Salir** | Muestra mensaje de despedida y termina la ejecución. |

> 💡 **Tip para mostrar listas largas:**
> ```java
> JTextArea area = new JTextArea(reporte, 15, 40);
> area.setEditable(false);
> JScrollPane scroll = new JScrollPane(area);
> JOptionPane.showMessageDialog(null, scroll, "Reportes", JOptionPane.INFORMATION_MESSAGE);
> ```

---

## 🏆 Criterios de Evaluación

| Criterio | Puntos |
|----------|--------|
| `sealed class` correctamente implementada con `permits` | 15 pts |
| `record` con constructor compacto y validación | 15 pts |
| Pattern Matching sin casting manual | 20 pts |
| Interfaz con `default method` funcional | 15 pts |
| Menú `JOptionPane` completo y fluido | 20 pts |
| Encapsulamiento correcto (`private`/`protected`) | 10 pts |
| Código limpio, comentarios técnicos y buenas prácticas | 5 pts |
| **Total** | **100 pts** |

---

## 🔥 Retos Bonus *(Para quien termine antes)*

- **Bonus 1 — Persistencia simulada:** Guarda los reportes en un archivo `.txt` con `BufferedWriter`. Al iniciar el sistema, carga los reportes previos si el archivo existe.
- **Bonus 2 — Switch moderno:** Refactoriza `procesarPersona()` para usar **Pattern Matching en `switch`** (Java 21).
- **Bonus 3 — Validación de duplicados:** Antes de registrar una persona, verifica que no exista ya un ID igual en la lista usando Streams.

---

## 📌 Notas para el Facilitador

- **Tiempo sugerido por fase:** ~20 min c/u + 15 min para el menú.
- El sistema no necesita base de datos. Usa listas en memoria (`ArrayList`).
- Los datos de prueba pueden ser cargados automáticamente al inicio del programa para agilizar la demo.
- El error de compilación de `Intruso` y el intento de mutar un `record` son **momentos pedagógicos clave** — detenerse a explicarlos suma mucho valor.

---

*"El código Legacy no es el pasado. Es el presente que no evolucionó a tiempo."* 🚀
