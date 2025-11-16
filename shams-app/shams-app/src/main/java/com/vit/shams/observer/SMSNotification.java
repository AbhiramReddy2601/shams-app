package com.vit.shams.observer;

import com.vit.shams.model.Patient;
import com.vit.shams.model.User;

public class SMSNotification implements NotificationObserver {
    private String apiKey;

    public SMSNotification() {
        this.apiKey = "SMS-API-KEY-12345";
    }

    @Override
    public void update(String message, User user) {
        if (user instanceof Patient) {
            Patient patient = (Patient) user;
            sendSMS(message, patient.getContactNumber());
        }
    }

    private void sendSMS(String content, String phoneNumber) {
        System.out.println("=== SMS NOTIFICATION ===");
        System.out.println("To: " + phoneNumber);
        System.out.println("From: SHAMS");
        System.out.println("Message: " + content);
        System.out.println("Sent via SMS Gateway with API Key: " + apiKey);
        System.out.println("=======================\n");
        
        // Simulate SMS sending delay
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}