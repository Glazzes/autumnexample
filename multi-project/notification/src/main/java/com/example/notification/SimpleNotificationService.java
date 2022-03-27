package com.example.notification;

import com.glaze.autumn.annotations.Service;

@Service(id = "simple")
public class SimpleNotificationService implements NotificationService {
    @Override
    public void sendNotification() {
        System.out.println("Sending a notification from com.example.notification package");
    }
}
