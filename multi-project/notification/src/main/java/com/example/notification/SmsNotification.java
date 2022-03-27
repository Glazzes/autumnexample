package com.example.notification;

import com.glaze.autumn.annotations.Service;

@Service(id = "sms")
public class SmsNotification implements NotificationService {
    @Override
    public void sendNotification() {
        System.out.println("Sending and sms notification");
    }
}
