package com.example.abw.controllers.user.car_advertisement;

import com.example.abw.exception.security.PrivacyViolationException;
import com.example.abw.model.advertisement.car_advertisement.CarAdvertisementDTOAdd;
import com.example.abw.model.advertisement.car_advertisement.CarAdvertisementResponse;
import com.example.abw.model.pageable.PageableParams;
import com.example.abw.servicies.CarAdvertisementService;
import com.example.abw.exception.entities.ResourceNotFoundException;
import com.example.abw.exception.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController("userCarAdController")
@RequestMapping("user/carAdvertisements")
public class CarAdvertisementController {

    @Autowired
    private CarAdvertisementService carAdvertisementService;

    @GetMapping("/{id}")//it is request to get resource with future possibility to change advertisement
    public ResponseEntity<?> getCarAd(@PathVariable("id") long id)
            throws ResourceNotFoundException, PrivacyViolationException {
        return new ResponseEntity<>(carAdvertisementService.findUserAdvertisement(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getAllByUser(PageableParams pageableParams) throws ResourceNotFoundException,ValidationException {
        return new ResponseEntity<>(carAdvertisementService.findAllByUser(pageableParams),HttpStatus.OK);
    }

    @GetMapping("/refresh/{id}")
    public ResponseEntity<?> refreshCarAdvertisement(@PathVariable("id") long id)
            throws ResourceNotFoundException, PrivacyViolationException {
        return new ResponseEntity<>(carAdvertisementService.refreshCarAdvertisement(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAd(@RequestBody CarAdvertisementDTOAdd carAdvertisementDTOAdd,
                                      @PathVariable("id") long id) throws ResourceNotFoundException,
            ValidationException, PrivacyViolationException {
        CarAdvertisementResponse carAd = carAdvertisementService.updateCarAdvertisement(carAdvertisementDTOAdd, id);
        return new ResponseEntity<>(carAd, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> softDeleteCarAd(@PathVariable("id") long id) throws ResourceNotFoundException,
            ValidationException, PrivacyViolationException {
        carAdvertisementService.softDelete(id);
        return new ResponseEntity<>("carAd deleted", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createAd(@RequestBody CarAdvertisementDTOAdd carAdvertisementDTOAdd)
            throws ValidationException, IOException, ResourceNotFoundException, PrivacyViolationException {
        CarAdvertisementResponse carAdvertisement = carAdvertisementService.createCarAdvertisement(carAdvertisementDTOAdd);
        return new ResponseEntity<>(carAdvertisement, HttpStatus.OK);
    }
}
