package com.riwi.notifications.model;

import com.riwi.notifications.enums.AlertLevel;

public class MessageLegacy {
    protected String recipient;
    private String content;
    private AlertLevel alertLevel;

    public MessageLegacy(String recipient, String content, AlertLevel alertLevel) {
        this.recipient = recipient;
        this.content = content;
        this.alertLevel = alertLevel;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AlertLevel getAlertLevel() {
        return alertLevel;
    }

    public void setAlertLevel(AlertLevel alertLevel) {
        this.alertLevel = alertLevel;
    }

    @Override
    public String toString() {
        return "MessageLegacy{" +
                "recipient='" + recipient + '\'' +
                ", content='" + content + '\'' +
                ", alertLevel=" + alertLevel +
                '}';
    }
}
