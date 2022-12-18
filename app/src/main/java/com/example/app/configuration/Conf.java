package com.example.app.configuration;

import com.example.app.services.FoodServices;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Conf {
    @Bean
    public FoodServices foodServices() {
        return new FoodServices();
    }
}
