package com.example.abw;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader() {
        return args -> {

        };
    }
}
