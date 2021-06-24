package com.example.abw;


import com.example.abw.repositories.currency.CurrencyExRepository;
import com.example.abw.servicies.logic.CarAdvertisementServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(
            CurrencyExRepository currencyExRepository

            ) {
        return args -> {
        };
    }
}
