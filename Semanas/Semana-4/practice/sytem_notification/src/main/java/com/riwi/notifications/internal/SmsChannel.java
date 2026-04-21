package com.riwi.notifications.internal;

import com.riwi.notifications.api.NotificationChannel;
import com.riwi.notifications.model.MessageRecord;

public sealed class SmsChannel implements NotificationChannel permits WhatsappChannel {

    protected String name;

    @Override
    public void sendMessage(MessageRecord message) {
        System.out.println("SMS: " + message);
    }
}
