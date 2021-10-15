package com.autumn.example.conguration

import com.glaze.autumn.annotations.Bean
import com.glaze.autumn.annotations.Configuration

@Configuration
class SecurityConfig {

    @Bean
    fun passwordEncoder(): PasswordEncoder{
        return PasswordEncoder()
    }

}

class PasswordEncoder{
    fun encode(password: String){
        println("$password has been encoded!!!")
    }
}