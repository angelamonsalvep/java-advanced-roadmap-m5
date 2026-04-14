package com.riwi;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String message = "We are on java training";

        HackerChannel hc = new HackerChannel();
        EmailChannel ec = new EmailChannel();
        OutlookEmailChannel oc = new OutlookEmailChannel();
        processMessage(message,oc, ec);

        NotificationChannel ec2 = new EmailChannel();
        NotificationChannel oc2 = new OutlookEmailChannel();

        processMessageWithInterface(message, ec2);
        processMessageWithInterface(message, oc2);

    }

    private static void processMessage(String message, OutlookEmailChannel oc, EmailChannel ec){
        oc.sendMessage(message);
        ec.sendMessage(message);
    }

    private static void processMessageWithInterface(String message, NotificationChannel notificationChannel){
        switch (notificationChannel){
            case OutlookEmailChannel o -> {
                System.out.println("Priorizando envío de correo...");
                o.sendMessage(message);
            }
            case EmailChannel e -> {
                System.out.println("Priorizando envío de correo...");
                e.sendMessage(message);
            }
            default -> throw new IllegalStateException("Unexpected value: " + notificationChannel);
        }
    }
}