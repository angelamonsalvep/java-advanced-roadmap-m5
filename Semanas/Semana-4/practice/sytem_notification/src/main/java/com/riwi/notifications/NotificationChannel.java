package com.riwi.notifications;

public sealed interface NotificationChannel permits EmailChannel,
        SmsChannel, SlackChannel {
    public void sendMessage(MessageRecord message);
}
