package com.autumn.example.notification

import com.glaze.autumn.annotations.Service

@Service(id = "push-service")
class PushNotificationService : NotificationService {
    override fun sendNotification(target: String) {
        println("Sending a push notification to $target")
    }
}