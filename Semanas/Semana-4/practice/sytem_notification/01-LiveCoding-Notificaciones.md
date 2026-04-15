# 🚀 Live Coding: El Guardián de Notificaciones (Sealed Edition)

## 📋 El Escenario
Estamos construyendo el núcleo de un sistema de alertas para una infraestructura crítica. Solo ciertos canales están autorizados para enviar mensajes. Si un desarrollador externo intenta "colar" un canal no verificado, el sistema debe rechazarlo en tiempo de compilación.

---

## 🛠️ El Reto en Vivo (30 min)

### Paso 1: La Vulnerabilidad (5 min)
*   Crear una interfaz `CanalNotificacion` (Legacy) con un método `enviar(String msg)`.
*   Demostrar cómo cualquier clase (ej. `CanalHackeado`) puede implementarla y saltarse nuestras reglas de seguridad.

### Paso 2: Sellando el Dominio (10 min)
*   Refactorizar `CanalNotificacion` a una **`sealed interface`**.
*   Autorizar únicamente a: `EmailCanal`, `SmsCanal` y `SlackCanal` mediante la cláusula `permits`.

### Paso 3: Definir el Futuro de las Subclases (10 min)
*   Implementar `EmailCanal` como `final` (No queremos más niveles de herencia en esta rama).
*   Implementar `SmsCanal` como `sealed` para permitir una subclase específica `WhatsappCanal`.
*   Implementar `SlackCanal` como `non-sealed` para permitir que el equipo de integraciones añada sus propios hooks sin restricciones.

### Paso 4: El "Checkmate" del Compilador (5 min)
*   Crear un método `procesar(CanalNotificacion canal)` que use un **`switch expression`**.
*   Observar cómo Java nos obliga a cubrir todos los casos y cómo rechaza cualquier intento de crear un `CanalAnonimo`.

---

## 💡 Tips para tu sesión:

*   **Provoca el error:** Intenta crear la clase `CanalAnonimo` antes de agregarla al `permits` para que vean el error `is not allowed in the sealed hierarchy`.
*   **Muestra el Switch:** Es la mejor forma de "vender" las Sealed Classes. Resalta que no necesitas un `default`, lo que garantiza que si mañana añades un canal, el código no compilará hasta que lo manejes en todos lados.
*   **Pregunta clave:** "¿Qué pasa si `SlackCanal` es `non-sealed`? ¿Seguimos teniendo control total sobre esa rama?". 
    *   *Respuesta:* No, ahí es donde delegamos la libertad de extensión a otros equipos, rompiendo el sellado en ese punto específico.

---
*Este ejercicio utiliza terminología de arquitectura moderna y demuestra por qué actualizarse a versiones recientes de Java (17+).*
