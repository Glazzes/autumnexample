package com.autumn.example.notification;

import com.glaze.autumn.annotations.Service;

@Service(id = "email-sender")
public class EmailNotificationService implements NotificationService {

    @Override
    public void sendNotification(String username) {
        System.out.println(String.format(
                "An email notification has been sent to %s",
                username+"@random.com"
        ));
    }
}
