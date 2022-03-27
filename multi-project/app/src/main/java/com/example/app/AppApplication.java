package com.example.app;

import com.example.clients.Client;
import com.example.notification.NotificationService;
import com.glaze.autumn.Autumn;
import com.glaze.autumn.annotations.Autowired;
import com.glaze.autumn.annotations.AutumnApplication;
import com.glaze.autumn.annotations.ComponentScan;
import com.glaze.autumn.annotations.Qualifier;
import com.glaze.autumn.application.CommandLineRunner;

@AutumnApplication
@ComponentScan(basePackages = {"com.example.app", "com.example.notification", "com.example.clients"})
public class AppApplication implements CommandLineRunner {

    @Autowired
    @Qualifier(id = "graphql")
    private Client client;

    private final NotificationService notificationService;
    public AppApplication(@Qualifier(id = "sms") NotificationService notificationService){
        this.notificationService = notificationService;
    }

    public static void main(String[] args) {
        Autumn.run(AppApplication.class);
    }

    @Override
    public void run() {
        client.sendRequest();
        notificationService.sendNotification();
    }
}
