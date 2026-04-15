package com.riwi.notifications;

public final class EmailChannel implements NotificationChannel {
    @Override
    public void sendMessage(MessageRecord message) {
        System.out.println("EMAIL: " + message);
    }
}
