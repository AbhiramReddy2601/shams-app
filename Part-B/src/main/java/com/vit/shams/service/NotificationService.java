package com.vit.shams.service;

import com.vit.shams.factory.NotificationFactory;
import com.vit.shams.model.User;
import com.vit.shams.observer.NotificationObserver;

import java.util.ArrayList;
import java.util.List;

public class NotificationService {
    private static NotificationService instance;
    private List<NotificationObserver> observers;
    private NotificationFactory factory;

    private NotificationService() {
        this.observers = new ArrayList<>();
        this.factory = new NotificationFactory();
        initializeObservers();
    }

    public static synchronized NotificationService getInstance() {
        if (instance == null) {
            instance = new NotificationService();
        }
        return instance;
    }

    private void initializeObservers() {
        // Register default notification channels
        attach(factory.createNotification("email"));
        attach(factory.createNotification("sms"));
        System.out.println("Notification service initialized with " + observers.size() + " observers");
    }

    public void attach(NotificationObserver observer) {
        observers.add(observer);
    }

    public void detach(NotificationObserver observer) {
        observers.remove(observer);
    }

    public void notifyAll(String message, User user) {
        System.out.println("\n=== SENDING NOTIFICATIONS ===");
        System.out.println("Message: " + message);
        System.out.println("User: " + user.getName());
        System.out.println("============================\n");

        for (NotificationObserver observer : observers) {
            observer.update(message, user);
        }
    }

    public void addNotificationChannel(String type) {
        NotificationObserver newObserver = factory.createNotification(type);
        attach(newObserver);
        System.out.println("Added new notification channel: " + type);
    }
}