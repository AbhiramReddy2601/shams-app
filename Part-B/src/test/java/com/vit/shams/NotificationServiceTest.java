package com.vit.shams;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Import service and factory classes
import com.vit.shams.service.NotificationService;
import com.vit.shams.factory.NotificationFactory;
import com.vit.shams.observer.NotificationObserver;
import com.vit.shams.observer.EmailNotification;
import com.vit.shams.observer.SMSNotification;

public class NotificationServiceTest {
    
    @Test
    void testNotificationService_Singleton() {
        NotificationService instance1 = NotificationService.getInstance();
        NotificationService instance2 = NotificationService.getInstance();
        
        assertSame(instance1, instance2, "NotificationService should be singleton");
    }
    
    @Test
    void testNotificationFactory() {
        NotificationFactory factory = new NotificationFactory();
        
        NotificationObserver email = factory.createNotification("email");
        NotificationObserver sms = factory.createNotification("sms");
        
        assertNotNull(email, "Email notification should be created");
        assertNotNull(sms, "SMS notification should be created");
        assertTrue(email instanceof EmailNotification, "Should be EmailNotification instance");
        assertTrue(sms instanceof SMSNotification, "Should be SMSNotification instance");
    }
}