package com.example.abw;


import com.example.abw.entities.currency.CurrencyEx;
import com.example.abw.repositories.currency.CurrencyExRepository;
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
          /*  List<CurrencyEx> currencyExes = currencyExRepository.findAll();
            for (CurrencyEx currencyEx : currencyExes){
                System.out.println(currencyEx.getCurrency());
            }*/
        };
    }
}
