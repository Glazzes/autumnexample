package com.autumn.example.conguration

import com.glaze.autumn.annotations.Bean
import com.glaze.autumn.annotations.Component
import com.glaze.autumn.annotations.Configuration

@Configuration
class SecurityConfig {


    fun passwordEncoder(): PasswordEncoder{
        return PasswordEncoder()
    }

}

@Component
class PasswordEncoder{
    fun encode(password: String){
        println("$password has been encoded!!!")
    }
}