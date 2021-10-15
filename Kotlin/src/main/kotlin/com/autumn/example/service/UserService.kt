package com.autumn.example.service

import com.autumn.example.conguration.PasswordEncoder
import com.autumn.example.notification.NotificationService
import com.autumn.example.repository.UserRepository
import com.glaze.autumn.annotations.Qualifier
import com.glaze.autumn.annotations.Service

@Service
class UserService (
    @Qualifier(id = "email-service")
    private val emailNotificationService: NotificationService,

    @Qualifier(id = "push-service")
    private val pushNotificationService: NotificationService,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
    ){

    fun saveUser(username: String, password: String){
        userRepository.save(username)
        passwordEncoder.encode(password)
        emailNotificationService.sendNotification("${username}@random.com")
        pushNotificationService.sendNotification(username)
    }

}