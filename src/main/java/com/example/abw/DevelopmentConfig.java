package com.example.abw;

import com.example.abw.entities.ad.Ad;
import com.example.abw.repositories.ad.CarAdRepository;
import com.example.abw.repositories.ad.image.car.CarImageRepository;
import com.example.abw.repositories.pagination.ad.car_ad.CarAdPaginationRepository;
import com.example.abw.repositories.sell_item.car.CarBrandRepository;
import com.example.abw.servicies.CarAdService;
import com.example.abw.servicies.UserService;
import com.example.abw.servicies.pagination.car_ad.with_one_param.CarAdPagServiceWithOneParam;
import com.example.abw.servicies.pagination.car_ad.simple.CarAdPaginationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class DevelopmentConfig {// delete car class!!!

    @Bean
    public CommandLineRunner dataLoader(CarBrandRepository carBrandRepository,
                                        CarAdRepository carAdRepository, UserService userService,
                                        CarImageRepository carImageRepository,
                                        CarAdPaginationRepository carAdPaginationRepository,
                                        CarAdService carAdService,
                                        AppProperties appProperties, CarAdPaginationService defaultCarAdPagService,
                                        CarAdPagServiceWithOneParam carAdPagByCarBrandDateDesc) {
        return args -> {
           
        };
    }
}
