# 📡 Live Coding: El Guardián de Notificaciones — Parte 2

> **Prerrequisito:** Haber completado la Parte 1 (Sealed Classes).  
> El sistema ya tiene `CanalNotificacion` sellado con `EmailCanal`, `SmsCanal`, `SlackCanal` y `WhatsappCanal`.

---

## 🗺️ Hoja de Ruta de Hoy

| Sesión | Tema | Duración |
|--------|------|----------|
| **Sesión A** | Records — Mensajes inmutables y trazables | ~25 min |
| **Sesión B** | Pattern Matching — Despacho limpio sin casting | ~25 min |
| **Sesión C** | Interfaces + Default Methods — Contratos que evolucionan | ~25 min |

---

## 📦 Sesión A: Records — El Mensaje que No Miente

### 🎯 Contexto

Hasta ahora el sistema solo define los **canales**. Pero, ¿qué viaja por ellos? Un `String` suelto no nos dice nada: ¿quién lo mandó?, ¿cuándo?, ¿es urgente?

En el sistema legacy, eso se resolvía con una clase `Mensaje` llena de getters/setters que cualquiera podía modificar en mitad del flujo. ¿El resultado? Mensajes alterados después de ser creados, bugs imposibles de rastrear.

Hoy lo sellamos también desde los datos.

---

### 🛠️ El Reto en Vivo

#### Paso 1: La Vulnerabilidad del Dato Mutable (5 min)

Crea esta clase legacy para mostrar el problema:

```java
// ❌ LEGACY — cualquiera puede alterar el mensaje en tránsito
public class MensajeLegacy {
    public String destinatario;
    public String contenido;
    public String nivel; // "INFO", "ALERTA", "CRITICO"
}
```

Muestra cómo en cualquier punto del flujo se puede hacer:
```java
mensaje.nivel = "INFO"; // alguien "baja" la urgencia de una alerta crítica
mensaje.contenido = ""; // alguien borra el contenido accidentalmente
```

**Pregunta al grupo:** *"¿Qué tan difícil sería rastrear este bug en producción?"*

---

#### Paso 2: El Record que Protege la Verdad (10 min)

Refactoriza a un `record` inmutable y con validación incorporada:

```java
public record Mensaje(
    String destinatario,
    String contenido,
    NivelAlerta nivel,
    LocalDateTime timestamp
) {
    // Constructor compacto: la validación ocurre ANTES de que el objeto exista
    public Mensaje {
        if (destinatario == null || destinatario.isBlank())
            throw new IllegalArgumentException("El destinatario no puede estar vacío");
        if (contenido == null || contenido.isBlank())
            throw new IllegalArgumentException("El contenido no puede estar vacío");
        // Normalizar: los destinatarios siempre en minúsculas
        destinatario = destinatario.toLowerCase().trim();
    }
}
```

Y el enum que cierra el dominio de niveles:

```java
public enum NivelAlerta { INFO, ALERTA, CRITICO }
```

---

#### Paso 3: El "Checkmate" del Record (5 min)

Demuestra los dos errores más reveladores:

```java
Mensaje m = new Mensaje("ops@empresa.com", "Servidor caído", NivelAlerta.CRITICO, LocalDateTime.now());

// ❌ Error de compilador: los campos de un record son final
m.contenido = "todo bien";

// ❌ Error en runtime: validación del constructor compacto
Mensaje invalido = new Mensaje("", "Hola", NivelAlerta.INFO, LocalDateTime.now());
```

**Momento pedagógico:** El primer error lo atrapa el compilador. El segundo, tu propia lógica de negocio. Los dos juntos hacen que un `Mensaje` corrupto sea **imposible de crear**.

---

#### Paso 4: Conectar con los Canales (5 min)

Actualiza la firma del método `enviar` en `CanalNotificacion`:

```java
public sealed interface CanalNotificacion permits EmailCanal, SmsCanal, SlackCanal {
    void enviar(Mensaje mensaje); // Ya no recibe un String suelto
}
```

Actualiza las implementaciones para usar `mensaje.contenido()`, `mensaje.destinatario()`, etc.

**Pregunta clave:** *"¿Por qué `mensaje.contenido()` y no `mensaje.getContenido()`?"*
> Los Records generan accessors con el mismo nombre del componente, sin prefijo `get`.

---

### 💡 Tips para esta sesión

- **Vende la igualdad automática:** Muestra que dos `Mensaje` con los mismos datos son `equals()` sin escribir una sola línea. Úsalo para deduplicar notificaciones.
- **Vende el `toString` gratis:** Haz `System.out.println(mensaje)` y muestra el output legible que genera el record automáticamente.

---

### 📌 Anotación: ¿Cuándo usar un Record y cuándo no?

Los Records son la herramienta correcta cuando el objeto **es** sus datos y nada más. Si el objeto además tiene comportamiento propio o estado que cambia con el tiempo, probablemente necesitas una clase normal.

**✅ Casos de uso ideales para Records**

| Caso | Ejemplo en este sistema |
|------|------------------------|
| **DTOs / Mensajes entre capas** | `Mensaje` viajando del despachador al canal — solo transporta datos, no actúa |
| **Respuestas de API** | `record ApiResponse(int status, String body, LocalDateTime at)` |
| **Resultados de operaciones** | `record ResultadoEnvio(boolean exito, int intentos, String canal)` |
| **Claves compuestas** | `record ClaveCanal(String tipo, String region)` usada como key en un `Map` |
| **Configuraciones inmutables** | `record ConfigSmtp(String host, int port, boolean tls)` leída al arrancar la app |
| **Value Objects de dominio** | `record Email(String valor)` — encapsula un email validado una sola vez |

**❌ Cuándo un Record NO es la herramienta correcta**

- El objeto necesita **estado mutable** — por ejemplo, un `Carrito` al que se le agregan productos.
- Necesitas **herencia** — los Records no pueden extender otras clases (aunque sí implementar interfaces).
- El objeto tiene **lógica de negocio compleja** más allá de validación en el constructor — un `Empleado` que calcula bonos, tiene jerarquía y ciclo de vida propio merece una clase completa.
- Necesitas **frameworks de persistencia** como JPA/Hibernate que requieren un constructor vacío y campos mutables para funcionar.

> 💬 **Regla mental rápida:** Si puedes describir el objeto como *"un [X] que tiene [datos]"*, es un Record. Si lo describes como *"un [X] que hace [cosas]"*, es una clase.

---

---

## 🔍 Sesión B: Pattern Matching — El Despachador Inteligente

### 🎯 Contexto

El sistema ya sabe qué canales existen (sellados) y qué mensajes viajan (inmutables). Ahora necesita un **despachador** que aplique lógica específica según el canal: los emails tienen asunto, los SMS tienen límite de caracteres, Slack tiene formato Markdown.

En el sistema legacy, esto se resolvía con un `instanceof` seguido de un casting manual. Hoy lo hacemos de forma segura y limpia.

---

### 🛠️ El Reto en Vivo

#### Paso 1: El Despachador Legacy (5 min)

Escribe primero el enfoque antiguo para que el contraste sea brutal:

```java
// ❌ LEGACY Java 8 — casting manual, propenso a ClassCastException
public static void despachar(CanalNotificacion canal, Mensaje mensaje) {
    if (canal instanceof EmailCanal) {
        EmailCanal email = (EmailCanal) canal; // casting manual
        email.configurarAsunto("[" + mensaje.nivel() + "] " + mensaje.contenido());
        email.enviar(mensaje);
    } else if (canal instanceof SmsCanal) {
        SmsCanal sms = (SmsCanal) canal; // otro casting
        if (mensaje.contenido().length() > 160) {
            System.out.println("SMS truncado");
        }
        sms.enviar(mensaje);
    }
    // SlackCanal olvidado... sin warning, sin error. Bug silencioso.
}
```

**Pregunta al grupo:** *"¿Qué problema grave tiene este código si mañana añadimos `TeamsCanal`?"*

---

#### Paso 2: Pattern Matching con `instanceof` (10 min)

```java
// ✅ Java 17 — Pattern Matching for instanceof
public static void despachar(CanalNotificacion canal, Mensaje mensaje) {
    if (canal instanceof EmailCanal email) {
        // 'email' ya está tipado, sin casting, sin riesgo
        email.configurarAsunto("[" + mensaje.nivel() + "] " + mensaje.contenido());
        email.enviar(mensaje);

    } else if (canal instanceof SmsCanal sms) {
        String contenidoFinal = mensaje.contenido().length() > 160
            ? mensaje.contenido().substring(0, 157) + "..."
            : mensaje.contenido();
        sms.enviar(new Mensaje(mensaje.destinatario(), contenidoFinal, mensaje.nivel(), mensaje.timestamp()));

    } else if (canal instanceof SlackCanal slack) {
        slack.enviar(mensaje);
    }
}
```

---

#### Paso 3: El Switch que lo Completa (10 min)

Lleva el método al nivel Java 21 usando **Pattern Matching en `switch`**:

```java
// ✅ Java 21 — Pattern Matching for switch (el combo perfecto con Sealed Classes)
public static String despachar(CanalNotificacion canal, Mensaje mensaje) {
    return switch (canal) {
        case EmailCanal email -> {
            email.configurarAsunto("[" + mensaje.nivel() + "] " + mensaje.contenido());
            email.enviar(mensaje);
            yield "📧 Email enviado a " + mensaje.destinatario();
        }
        case SmsCanal sms -> {
            String txt = mensaje.contenido().length() > 160
                ? mensaje.contenido().substring(0, 157) + "..."
                : mensaje.contenido();
            sms.enviar(new Mensaje(mensaje.destinatario(), txt, mensaje.nivel(), mensaje.timestamp()));
            yield "📱 SMS enviado (" + txt.length() + " caracteres)";
        }
        case WhatsappCanal wa -> {
            wa.enviar(mensaje);
            yield "💬 WhatsApp enviado con confirmación de lectura";
        }
        case SlackCanal slack -> {
            slack.enviar(mensaje);
            yield "🔔 Slack notificado en el canal #alertas";
        }
        // No necesitas default — el compilador sabe que cubriste todos los casos
    };
}
```

**Momento pedagógico — el "Checkmate" del switch:**
Añade temporalmente un nuevo `permits TelegramCanal` a la interfaz sellada. El método `despachar` dejará de compilar de inmediato. **El compilador te obliga a manejar el nuevo canal.** Ningún bug puede colarse silenciosamente.

---

#### Paso 4: Filtrado por Nivel de Alerta (5 min)

Agrega guards al switch para demostrar el poder de los **guarded patterns**:

```java
case EmailCanal email when mensaje.nivel() == NivelAlerta.CRITICO -> {
    email.configurarAsunto("🚨 CRÍTICO: " + mensaje.contenido());
    email.marcarComoUrgente();
    email.enviar(mensaje);
    yield "📧 Email CRÍTICO enviado con prioridad máxima";
}
case EmailCanal email -> {
    email.configurarAsunto("[" + mensaje.nivel() + "] " + mensaje.contenido());
    email.enviar(mensaje);
    yield "📧 Email enviado a " + mensaje.destinatario();
}
```

**Pregunta clave:** *"¿En qué orden evalúa Java los casos del switch? ¿Qué pasa si ponemos el caso genérico primero?"*

---

### 💡 Tips para esta sesión

- **El error del `default` faltante** es el mejor momento de la sesión. Bórralo deliberadamente con la sealed interface activa y deja que el compilador hable.
- **Compara en pantalla partida:** Java 8 a la izquierda, Java 17/21 a la derecha. El contraste visual convence más que cualquier argumento.

---

---

## 🧬 Sesión C: Interfaces + Default Methods — El Contrato que Evoluciona

### 🎯 Contexto

El sistema ya despacha mensajes. Ahora el equipo de operaciones pide dos cosas nuevas:
1. Que cada canal pueda **auditar** cada envío (registrar quién mandó qué y cuándo).
2. Que los canales críticos puedan **reintentar** el envío si falla.

El problema: ya tenemos canales implementados en producción. Añadir métodos abstractos nuevos a `CanalNotificacion` rompería todo. Aquí entran los **Default Methods**.

---

### 🛠️ El Reto en Vivo

#### Paso 1: El Problema del Contrato Rígido (5 min)

Simula el escenario del pasado: añade un método abstracto a la interfaz sellada y observa cómo todos los canales existentes fallan de inmediato:

```java
// Añade esto a CanalNotificacion y muestra los errores en cadena:
void auditar(Mensaje mensaje); // 💥 rompe EmailCanal, SmsCanal, SlackCanal al instante
```

**Pregunta al grupo:** *"¿Cuántas clases tendrías que tocar en un sistema real con 20 canales?"*

Elimina el método abstracto. Ahora la solución elegante.

---

#### Paso 2: Auditoría con Default Method (10 min)

Añade la funcionalidad sin romper nada:

```java
public sealed interface CanalNotificacion permits EmailCanal, SmsCanal, SlackCanal {

    void enviar(Mensaje mensaje);

    // Nuevo comportamiento, cero impacto en implementaciones existentes
    default void auditar(Mensaje mensaje) {
        System.out.printf("[AUDIT] %s | Canal: %s | Destino: %s | Nivel: %s%n",
            mensaje.timestamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
            this.getClass().getSimpleName(),
            mensaje.destinatario(),
            mensaje.nivel()
        );
    }
}
```

Ahora actualiza el despachador para que llame a `auditar` antes de `enviar`:

```java
case EmailCanal email -> {
    canal.auditar(mensaje);   // comportamiento compartido gratis
    email.enviar(mensaje);
    yield "📧 Email enviado y auditado";
}
```

---

#### Paso 3: Contrato de Reintento (10 min)

Crea una segunda interfaz para los canales que soporten reintentos:

```java
public interface Reintentable {

    int MAX_INTENTOS = 3; // constante de interfaz

    boolean enviarConFallo(Mensaje mensaje); // simula un envío que puede fallar

    default void enviarConReintento(Mensaje mensaje) {
        int intentos = 0;
        boolean exito = false;

        while (intentos < MAX_INTENTOS && !exito) {
            intentos++;
            System.out.println("🔄 Intento " + intentos + " de " + MAX_INTENTOS + "...");
            exito = enviarConFallo(mensaje);
        }

        if (exito) {
            System.out.println("✅ Enviado exitosamente en el intento " + intentos);
        } else {
            System.out.println("❌ Falló después de " + MAX_INTENTOS + " intentos. Escalando a CRÍTICO.");
        }
    }
}
```

Haz que solo `EmailCanal` implemente `Reintentable` (los emails críticos necesitan reintentos, los SMS no):

```java
public final class EmailCanal implements CanalNotificacion, Reintentable {
    // ...
    @Override
    public boolean enviarConFallo(Mensaje mensaje) {
        // Simula un 60% de éxito
        boolean exito = Math.random() > 0.4;
        if (exito) System.out.println("📧 Email entregado: " + mensaje.destinatario());
        else System.out.println("📧 Fallo de red al enviar a: " + mensaje.destinatario());
        return exito;
    }
}
```

---

#### Paso 4: El Problema del Diamante (5 min)

Demuestra el escenario de conflicto y cómo Java lo resuelve:

```java
// Si dos interfaces tienen el mismo default method:
interface InterfazA {
    default String identificar() { return "Soy A"; }
}
interface InterfazB {
    default String identificar() { return "Soy B"; }
}

// EmailCanal implementa ambas — el compilador EXIGE resolución explícita
public final class EmailCanal implements CanalNotificacion, Reintentable, InterfazA, InterfazB {
    @Override
    public String identificar() {
        return InterfazA.super.identificar(); // tú decides quién gana
    }
}
```

**Pregunta clave:** *"¿Por qué Java eligió este mecanismo en lugar de simplemente prohibir el conflicto?"*

---

### 💡 Tips para esta sesión

- **El orden importa para la historia:** Primero muestra el dolor (añadir método abstracto y ver todo rojo), luego la solución. El contraste emocional fija el concepto.
- **`Math.random()` en `enviarConFallo` hace la demo divertida** — corre el reintento varias veces en vivo para que el grupo vea el comportamiento no determinístico.
- **Conecta con el mundo real:** `List.forEach()`, `Collection.stream()` y `Map.getOrDefault()` existen gracias a default methods. Sin ellos, Java 8 hubiera roto toda app que usara colecciones.

---

---

## 🏁 Cierre: El Sistema Completo

Al terminar las tres sesiones, el sistema tiene esta arquitectura:

```
CanalNotificacion (sealed interface)
│   ├── enviar(Mensaje) — abstracto
│   └── auditar(Mensaje) — default
│
├── EmailCanal (final) implements CanalNotificacion, Reintentable
├── SmsCanal (sealed) implements CanalNotificacion
│   └── WhatsappCanal (final)
└── SlackCanal (non-sealed) implements CanalNotificacion

Mensaje (record)
│   ├── destinatario: String
│   ├── contenido: String
│   ├── nivel: NivelAlerta (enum)
│   └── timestamp: LocalDateTime

Despachador
│   └── despachar(CanalNotificacion, Mensaje) — switch con Pattern Matching

Reintentable (interface)
│   ├── enviarConFallo(Mensaje) — abstracto
│   └── enviarConReintento(Mensaje) — default
```

**Pregunta final para el grupo:**
> *"¿Qué hubiera costado añadir `WhatsappCanal` al sistema legacy original? ¿Y al sistema que construimos hoy?"*

---

## 🔥 Reto Bonus

Para quienes terminen antes: implementa un `DespachadorMasivo` que reciba una `List<CanalNotificacion>` y un `List<Mensaje>`, y use **Streams** para:
1. Filtrar solo los mensajes de nivel `CRITICO`.
2. Despacharlos únicamente por canales que sean instancias de `Reintentable`.
3. Imprimir un resumen de cuántos mensajes fueron enviados por cada tipo de canal.

---

*"Un sistema bien diseñado no teme al cambio. Lo anticipa."* 🛡️
