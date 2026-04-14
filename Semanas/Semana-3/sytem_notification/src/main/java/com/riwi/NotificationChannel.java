package com.riwi;

public sealed interface NotificationChannel permits EmailChannel{
    public void sendMessage(String message);
}
