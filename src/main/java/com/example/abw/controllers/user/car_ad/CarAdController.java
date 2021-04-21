package com.example.abw.controllers.user.car_ad;

import com.example.abw.entities.ad.Ad;
import com.example.abw.entities.ad.CarAd;
import com.example.abw.model.car_ad.CarAdRequest;
import com.example.abw.security.CustomUserDetails;
import com.example.abw.servicies.CarAdService;
import com.example.abw.exception.entities.ResourceNotFoundException;
import com.example.abw.exception.validation.ValidationException;
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
    private CarAdService carAdService;

    @GetMapping("/{id}")//it is request to get resource with future possibility to change ad
    public ResponseEntity<?> getCarAd(@PathVariable("id") long id) throws ResourceNotFoundException {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        System.out.println(customUserDetails.getUsername());
        CarAd carAd = carAdService.findById(id);
        if (carAd.getUser().getEmail().equals(customUserDetails.getUsername())) {
            return new ResponseEntity<>(carAd, HttpStatus.OK);
        } else return new ResponseEntity<>("resource not found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAd(@RequestBody CarAdRequest carAdRequest,
                                      @PathVariable("id") long id) throws ResourceNotFoundException, ValidationException {
        CarAd carAd = carAdService.updateCarAd(carAdRequest, id);
        return new ResponseEntity<>(carAd, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> softDeleteCarAd(@PathVariable("id") long id) throws ResourceNotFoundException,
            ValidationException {
        carAdService.softDelete(id);
        return new ResponseEntity<>("carAd deleted", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createAd(@RequestBody CarAdRequest carAdRequest) throws ValidationException {
        Ad carAd = carAdService.createCarAd(carAdRequest);
        return new ResponseEntity<>(carAd, HttpStatus.OK);
    }
}
