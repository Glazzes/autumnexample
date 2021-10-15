package com.autumn.example.service;

import com.autumn.example.config.PasswordEncoder;
import com.autumn.example.notification.NotificationService;
import com.autumn.example.repository.UserRepository;
import com.glaze.autumn.annotations.Autowired;
import com.glaze.autumn.annotations.Qualifier;
import com.glaze.autumn.annotations.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Qualifier(id = "email-sender")
    @Autowired
    private NotificationService emailNotificationSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final NotificationService smsNotificationService;
    public UserService(@Qualifier(id = "sms-sender") NotificationService smsNotificationService){
        this.smsNotificationService = smsNotificationService;
    }

    public void save(String username, String password){
        userRepository.save(username);
        passwordEncoder.encode(password);
        emailNotificationSender.sendNotification(username);
        smsNotificationService.sendNotification(username);
    }

}
