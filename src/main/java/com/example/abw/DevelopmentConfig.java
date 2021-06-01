package com.example.abw;


import com.example.abw.entities.advertisement.CarAdvertisement;
import com.example.abw.entities.currency.CurrencyEx;
import com.example.abw.model.currency.Currency;
import com.example.abw.repositories.advertisement.CarAdvertisementRepository;
import com.example.abw.repositories.currency.CurrencyExRepository;
import com.example.abw.utils.currency.CurrencyUtil;
import com.example.abw.utils.message.MessageTextUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;


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
