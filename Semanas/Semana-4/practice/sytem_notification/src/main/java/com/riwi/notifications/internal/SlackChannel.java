package com.riwi.notifications.internal;

import com.riwi.notifications.api.NotificationChannel;
import com.riwi.notifications.model.MessageRecord;

public non-sealed class SlackChannel implements NotificationChannel {
    @Override
    public void sendMessage(MessageRecord message) {
        System.out.println("SLACK: " + message);
    }
}
