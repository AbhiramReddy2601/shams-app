package com.vit.shams.observer;

import com.vit.shams.model.User;

public interface NotificationObserver {
    void update(String message, User user);
}