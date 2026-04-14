/**
 * SOLUCIÓN: El Guardián de Notificaciones (Sealed Edition)
 * Este archivo contiene la progresión completa del ejercicio para guiar el Live Coding.
 */

public class SolucionNotificaciones {

    public static void main(String[] args) {
        CanalNotificacion email = new EmailCanal();
        CanalNotificacion sms = new WhatsappCanal();
        CanalNotificacion slack = new SlackCanal();

        System.out.println("--- Procesando Notificaciones ---");
        procesar(email);
        procesar(sms);
        procesar(slack);

        // Paso 1: Demostrar vulnerabilidad (Descomentar para mostrar el error después de sellar)
        // CanalNotificacion hack = new CanalHackeado(); 
        // procesar(hack);
    }

    /**
     * Paso 4: El "Checkmate" del Compilador
     * Al ser una jerarquía sellada, el compilador sabe exactamente qué clases pueden existir.
     * No se requiere una cláusula 'default', lo que garantiza seguridad total.
     */
    public static void procesar(CanalNotificacion canal) {
        String mensaje = switch (canal) {
            case EmailCanal e -> "📧 Enviando correo corporativo...";
            case SmsCanal s -> "📱 Enviando SMS (incluye derivados como Whatsapp)...";
            case SlackCanal sl -> "💬 Enviando alerta a Slack (Canal Abierto/Non-sealed)...";
        };
        System.out.println(mensaje);
        canal.enviar("Alerta de sistema crítico");
    }
}

// ==========================================
// Paso 2: Sellando el Dominio
// ==========================================
sealed interface CanalNotificacion permits EmailCanal, SmsCanal, SlackCanal {
    void enviar(String msg);
}

// ==========================================
// Paso 3: Definir el Futuro de las Subclases
// ==========================================

// Email: No queremos que nadie más herede de aquí.
final class EmailCanal implements CanalNotificacion {
    @Override
    public void enviar(String msg) {
        System.out.println("[EMAIL] " + msg);
    }
}

// SMS: Sellada para permitir SOLO Whatsapp.
sealed class SmsCanal implements CanalNotificacion permits WhatsappCanal {
    @Override
    public void enviar(String msg) {
        System.out.println("[SMS] " + msg);
    }
}

// Whatsapp: Destino final de la rama SMS.
final class WhatsappCanal extends SmsCanal {
    @Override
    public void enviar(String msg) {
        System.out.println("[WHATSAPP] " + msg);
    }
}

// Slack: Permitimos que otros equipos extiendan (non-sealed).
non-sealed class SlackCanal implements CanalNotificacion {
    @Override
    public void enviar(String msg) {
        System.out.println("[SLACK] " + msg);
    }
}

// ==========================================
// El Intento de Invasión
// ==========================================
/* 
// Esta clase causará un error de compilación: 
// "class is not allowed in the sealed hierarchy"
class CanalHackeado implements CanalNotificacion {
    @Override
    public void enviar(String msg) {
        System.out.println("[HACK] Robando datos: " + msg);
    }
}
*/
