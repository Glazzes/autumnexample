package com.example.app;

import com.example.clients.Client;
import com.example.notification.NotificationService;
import com.glaze.autumn.Autumn;
import com.glaze.autumn.annotations.*;

@AutumnApplication
@ComponentScan(basePackages = {"com.example.app", "com.example.notification", "com.example.clients"})
public class AppApplication {

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

    @MainMethod
    public void run() {
        client.sendRequest();
        notificationService.sendNotification();
    }
}
