package com.riwi;

public sealed interface CanalNotificacion permits SmsNotificacion, EmailNotificacion{
    public void enviarMensaje(String mensaje);
}
