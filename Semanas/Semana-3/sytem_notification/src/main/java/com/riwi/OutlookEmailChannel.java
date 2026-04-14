package com.riwi;

public class OutlookEmailChannel extends EmailChannel{
    @Override
    public void sendMessage(String message) {
        System.out.println("Notificacion enviada desde Outlook: " + message);
    }
}
