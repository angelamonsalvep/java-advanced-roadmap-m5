package com.riwi.notifications;

public sealed class SmsChannel implements NotificationChannel permits WhatsappChannel {

    protected String name;

    @Override
    public void sendMessage(MessageRecord message) {
        System.out.println("SMS: " + message);
    }
}
