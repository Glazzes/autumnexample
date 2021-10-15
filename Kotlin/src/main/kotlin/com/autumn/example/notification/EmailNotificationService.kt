package com.autumn.example.notification

import com.glaze.autumn.annotations.Service

@Service(id = "email-service")
class EmailNotificationService : NotificationService {
    override fun sendNotification(target: String) {
        println("Sending email notification to $target")
    }
}