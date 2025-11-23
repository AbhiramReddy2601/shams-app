package com.vit.shams.factory;

import com.vit.shams.observer.EmailNotification;
import com.vit.shams.observer.NotificationObserver;
import com.vit.shams.observer.SMSNotification;

public class NotificationFactory {
    public NotificationObserver createNotification(String type) {
        switch (type.toLowerCase()) {
            case "email":
                return new EmailNotification();
            case "sms":
                return new SMSNotification();
            default:
                throw new IllegalArgumentException("Unknown notification type: " + type);
        }
    }
}