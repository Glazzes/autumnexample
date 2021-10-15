package com.autumn.example.config;

import com.glaze.autumn.annotations.Bean;
import com.glaze.autumn.annotations.Configuration;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new PasswordEncoder();
    }

}
