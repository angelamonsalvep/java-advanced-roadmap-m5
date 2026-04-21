package com.riwi.notifications.api;

import com.riwi.notifications.internal.EmailChannel;
import com.riwi.notifications.model.MessageRecord;
import com.riwi.notifications.internal.SlackChannel;
import com.riwi.notifications.internal.SmsChannel;

public sealed interface NotificationChannel permits EmailChannel,
        SmsChannel, SlackChannel {
    public void sendMessage(MessageRecord message);
}
