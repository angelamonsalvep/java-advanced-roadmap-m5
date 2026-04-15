package com.riwi.notifications;

public non-sealed class SlackChannel implements NotificationChannel {
    @Override
    public void sendMessage(MessageRecord message) {
        System.out.println("SLACK: " + message);
    }
}
