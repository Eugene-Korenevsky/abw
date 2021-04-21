package com.example.abw;

import com.example.abw.entities.user.User;
import com.example.abw.security.jwt.JwtProvider;
import com.example.abw.servicies.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(
            UserService userServiceImpl,
            JwtProvider jwtProvider
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
