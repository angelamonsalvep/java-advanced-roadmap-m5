package com.riwi.notifications;

import java.time.LocalDateTime;

public record MessageRecord(String receiver, String content,
                            AlertLevel alertLevel, LocalDateTime timeStamp) {
    public MessageRecord {
        if(receiver==null || receiver.isBlank()){
            throw new IllegalArgumentException("The receiver can´t be empty");
        }
        if(content==null || content.isBlank()){
            throw  new IllegalArgumentException("The content can´t be empty");
        }
    }
}
