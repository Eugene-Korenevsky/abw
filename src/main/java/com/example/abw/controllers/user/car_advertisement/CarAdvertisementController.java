package com.example.abw.controllers.user.car_advertisement;

import com.example.abw.entities.advertisement.Advertisement;
import com.example.abw.entities.advertisement.CarAdvertisement;
import com.example.abw.model.car_advertisement.CarAdvertisementRequest;
import com.example.abw.security.CustomUserDetails;
import com.example.abw.servicies.CarAdvertisementService;
import com.example.abw.exception.entities.ResourceNotFoundException;
import com.example.abw.exception.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController("userCarAdController")
@RequestMapping("user/carAdvertisements")
public class CarAdvertisementController {

    @Autowired
    private CarAdvertisementService carAdvertisementService;

    @GetMapping("/{id}")//it is request to get resource with future possibility to change ad
    public ResponseEntity<?> getCarAd(@PathVariable("id") long id) throws ResourceNotFoundException {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        CarAdvertisement carAd = carAdvertisementService.findById(id);
        if (carAd.getUser().getEmail().equals(customUserDetails.getUsername())) {
            return new ResponseEntity<>(carAd, HttpStatus.OK);
        } else return new ResponseEntity<>("resource not found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAd(@RequestBody CarAdvertisementRequest carAdvertisementRequest,
                                      @PathVariable("id") long id) throws ResourceNotFoundException, ValidationException {
        CarAdvertisement carAd = carAdvertisementService.updateCarAdvertisement(carAdvertisementRequest, id);
        return new ResponseEntity<>(carAd, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> softDeleteCarAd(@PathVariable("id") long id) throws ResourceNotFoundException,
            ValidationException {
        carAdvertisementService.softDelete(id);
        return new ResponseEntity<>("carAd deleted", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createAd(@RequestBody CarAdvertisementRequest carAdvertisementRequest) throws ValidationException, IOException {
        Advertisement carAdvertisement = carAdvertisementService.createCarAdvertisement(carAdvertisementRequest);
        return new ResponseEntity<>(carAdvertisement, HttpStatus.OK);
    }
}
