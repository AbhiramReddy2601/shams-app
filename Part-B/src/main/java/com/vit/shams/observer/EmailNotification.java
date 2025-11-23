package com.vit.shams.observer;

import com.vit.shams.model.User;

public class EmailNotification implements NotificationObserver {
    private String smtpServer;

    public EmailNotification() {
        this.smtpServer = "smtp.shams.com";
    }

    @Override
    public void update(String message, User user) {
        sendEmail(message, user.getEmail());
    }

    private void sendEmail(String content, String recipient) {
        System.out.println("=== EMAIL NOTIFICATION ===");
        System.out.println("To: " + recipient);
        System.out.println("From: appointments@shams.com");
        System.out.println("Subject: SHAMS Appointment Update");
        System.out.println("Body: " + content);
        System.out.println("Sent via: " + smtpServer);
        System.out.println("==========================\n");
        
        // Simulate email sending delay
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}