package com.example.abw;

import com.example.abw.entities.advertisement.CarAdvertisement;
import com.example.abw.entities.currency.CurrencyEntity;
import com.example.abw.model.advertisement.car_advertisement.CarAdvertisementMapper;
import com.example.abw.model.currency.Currency;
import com.example.abw.repositories.advertisement.CarAdvertisementRepository;
import com.example.abw.repositories.currency.CurrencyEntityRepository;
import com.example.abw.repositories.currency.CurrencyExchangeRepository;
import com.example.abw.security.jwt.JwtProvider;
import com.example.abw.servicies.CarAdvertisementService;
import com.example.abw.servicies.CurrencyExchangeService;
import com.example.abw.servicies.UserService;
import com.example.abw.servicies.business_processors.ActiveCarAdvertisements;
import com.example.abw.utils.currency.CurrencyUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;


@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(
            UserService userServiceImpl,
            JwtProvider jwtProvider,
            CurrencyExchangeRepository currencyExchangeRepository,
            ActiveCarAdvertisements activeCarAdvertisements,
            CarAdvertisementRepository carAdvertisementRepository,
            CarAdvertisementService carAdvertisementService,
            AppProperties appProperties,
            CurrencyUtil currencyUtil,
            CurrencyEntityRepository currencyEntityRepository,
            CarAdvertisementMapper carAdvertisementMapper,
            CurrencyExchangeService currencyExchangeService
    ) {
        return args -> {
            List<CarAdvertisement> carAdvertisements = carAdvertisementService.findAll();
            CarAdvertisement carAdvertisement = carAdvertisementService.findById(4L);
            System.out.println(currencyExchangeService.getPrice(
                    Currency.BYN, Currency.USD, carAdvertisement.getPrice()));


        };
    }
}
