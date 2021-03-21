package com.example.abw.controllers.user.car_ad;

import com.example.abw.entities.ad.CarAd;
import com.example.abw.servicies.CarAdService;
import com.example.abw.servicies.exceptions.ResourceNotFoundException;
import com.example.abw.validator.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController("userCarAdController")
@RequestMapping("user/carAds")
public class CarAdController {

    @Autowired
    private CarAdService carAdServiceImpl;
    @Autowired
    private CarAdService carAdService;

    @GetMapping("/{id}")//it is request to get resource with future possibility to change ad
    public ResponseEntity<?> getCarAd(@PathVariable("id") long id) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        org.springframework.security.core.userdetails.User user =
                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        try {
            CarAd carAd = carAdServiceImpl.findById(id);
            if (carAd.getUser().getEmail().equals(user.getUsername())) {
                return new ResponseEntity<>(carAd, HttpStatus.OK);
            } else return new ResponseEntity<>("resource not found", HttpStatus.NOT_FOUND);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAd(@RequestBody CarAd carAd, @PathVariable("id") long id) {
        try {
            carAd = carAdService.update(carAd, id);
            return new ResponseEntity<>(carAd, HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getFullMessage(), HttpStatus.BAD_REQUEST);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}