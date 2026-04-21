package com.riwi.notifications.internal;

import com.riwi.notifications.model.MessageRecord;
import com.riwi.notifications.api.NotificationChannel;

public final class EmailChannel implements NotificationChannel {
    @Override
    public void sendMessage(MessageRecord message) {
        System.out.println("EMAIL: " + message);
    }
}
