package com.riwi;

import com.riwi.notifications.*;

import java.time.LocalDateTime;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String message = "We are on java training";

        var messageLegacy = new MessageLegacy("angela@riwi.io",
                "Server down", AlertLevel.CRITICAL);
        System.out.println(messageLegacy);

        var messageLegacy2 = new MessageLegacy("angela@riwi.io",
                "Server down", AlertLevel.CRITICAL);
        System.out.println(messageLegacy);

        boolean equalsL = messageLegacy.equals(messageLegacy2);
        System.out.println("isEqualLegacy: " + equalsL);
        

        var messageRecord = new MessageRecord("juanc@riwi.io", "Happy Birthday JuanC", AlertLevel.INFO,
                LocalDateTime.parse("2023-10-27T10:15:30"));
        var messageRecord2 = new MessageRecord("juanc@riwi.io", "Happy Birthday JuanC", AlertLevel.INFO,
                LocalDateTime.parse("2023-10-27T10:15:30"));

        boolean equals = messageRecord.equals(messageRecord2);
        System.out.println("isEqualRecord: " + equals);

        System.out.println(messageRecord);

        HackerChannel hc = new HackerChannel();
        EmailChannel ec = new EmailChannel();
        SmsChannel smsChannel = new SmsChannel();
        System.out.println("---Notification using only classes---");
        processMessage(messageRecord,smsChannel, ec);

        NotificationChannel ec2 = new EmailChannel();
        NotificationChannel smsChannel2 = new SmsChannel();

        System.out.println("---Notification using interfaces---");
        processMessageWithInterface(messageRecord, ec2);
        processMessageWithInterface(messageRecord, smsChannel2);
        processMessageWithInterface(messageRecord, new SlackChannel());

    }

    private static void processMessage(MessageRecord messageRecord, SmsChannel smsChannel, EmailChannel ec){
        smsChannel.sendMessage(messageRecord);
        ec.sendMessage(messageRecord);
    }

    private static void processMessageWithInterface(MessageRecord messageRecord, NotificationChannel notificationChannel){
        switch (notificationChannel){
            case SmsChannel o -> {
                System.out.println("Prioritizing SMS's send...");
                o.sendMessage(messageRecord);
            }
            case EmailChannel e -> {
                System.out.println("Prioritizing Email's send...");
                e.sendMessage(messageRecord);
            }
            case SlackChannel s -> {
                System.out.println("Prioritizing Slack's send...");
                s.sendMessage(messageRecord);
            }
        }
    }
}