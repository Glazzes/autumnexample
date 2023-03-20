package com.example.notification;

import com.glaze.autumn.annotations.Service;

@Service(id = "simple")
public class PhoneCallNotificationService implements NotificationService {
    @Override
    public void sendNotification() {
        System.out.println("Sending a phone call to your phone!!!");
    }
}
