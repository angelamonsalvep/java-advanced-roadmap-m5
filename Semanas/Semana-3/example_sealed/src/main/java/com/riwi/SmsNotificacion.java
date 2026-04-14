package com.riwi;

public sealed class SmsNotificacion implements CanalNotificacion permits WhatsappCanal{

    @Override
    public void enviarMensaje(String mensaje) {
        System.out.println("[SMS] " + mensaje);
    }
}
