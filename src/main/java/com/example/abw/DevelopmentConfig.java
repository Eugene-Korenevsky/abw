package com.example.abw;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;


@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(

    ) {
        return args -> {
            String s = "abc";
            String sd = "cba";
            System.out.println(s.hashCode());
            System.out.println(sd.hashCode());
        };
    }
}
