package com.example.abw.controllers.general.car_advertisement;

import com.example.abw.entities.user.User;
import com.example.abw.exception.user.EmailIsAlreadyExistException;
import com.example.abw.exception.user.UserOrPasswordException;
import com.example.abw.model.auth.AuthRequest;
import com.example.abw.model.auth.RegistrationRequest;
import com.example.abw.security.jwt.JwtProvider;
import com.example.abw.servicies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
public class AuthController {
    @Autowired
    private UserService userServiceImpl;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @RequestBody @Valid RegistrationRequest registrationRequest) throws EmailIsAlreadyExistException {
        User user = new User();
        user.setPassword(registrationRequest.getPassword());
        user.setEmail(registrationRequest.getEmail());
        user.setPhoneNumber(registrationRequest.getPhoneNumber());
        user.setName(registrationRequest.getName());
        userServiceImpl.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/auth")
    public ResponseEntity<?> auth(
            @RequestBody AuthRequest request, HttpServletResponse response) throws UserOrPasswordException {
        User user = userServiceImpl.findByEmailAndPassword(request.getEmail(), request.getPassword());
        String token = jwtProvider.generateToken(user.getEmail());
        response.addCookie(new Cookie("tokenC", token));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
