package com.example.abw;

import com.example.abw.entities.advertisement.CarAdvertisement;
import com.example.abw.repositories.advertisement.CarAdvertisementRepository;
import com.example.abw.security.jwt.JwtProvider;
import com.example.abw.servicies.UserService;
import com.example.abw.servicies.business_processors.ActiveCarAdvertisements;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(
            UserService userServiceImpl,
            JwtProvider jwtProvider,
            ActiveCarAdvertisements activeCarAdvertisements,
            CarAdvertisementRepository carAdvertisementRepository
    ) {
        return args -> {
            /*User user = new User();
            user.setPassword("karager");
            user.setEmail("kara-91@gmail.com");
            user.setName("Eugene");
            user.setPhoneNumber("+375297320231");
            //userServiceImpl.saveUser(user);
            User user1 = userServiceImpl.findByEmailAndPassword("kara-91@gmail.com", "karager");
            System.out.println(user1);
            String token = jwtProvider.generateToken(user.getEmail());
            System.out.println("first " + token);
            System.out.println("second " + jwtProvider.getLoginFromToken(token));
            System.out.println("third " + jwtProvider.validateToken(token));*/
        };
    }
}
