package com.autumn.example.notification;

import com.glaze.autumn.annotations.Service;

@Service(id = "sms-sender")
public class PushNotificationService implements NotificationService {

    @Override
    public void sendNotification(String target) {
        System.out.println("A sms notification has been sent to " + target);
    }

}
