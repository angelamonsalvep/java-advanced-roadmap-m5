package com.riwi;


public class Main{
    public static void main(String[] args) {

        System.out.printf("Hello and welcome!");



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