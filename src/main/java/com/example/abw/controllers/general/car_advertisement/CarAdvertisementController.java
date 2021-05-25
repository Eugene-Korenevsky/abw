package com.example.abw.controllers.general.car_advertisement;

import com.example.abw.entities.advertisement.Advertisement;
import com.example.abw.entities.advertisement.CarAdvertisement;
import com.example.abw.exception.validation.ValidationException;
import com.example.abw.model.advertisement.car_advertisement.CarAdvertisementResponse;
import com.example.abw.model.currency.Currency;
import com.example.abw.model.pageable.PageableParams;
import com.example.abw.servicies.CarAdvertisementService;
import com.example.abw.exception.entities.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/carAdvertisements", produces = "application/json")
public class CarAdvertisementController {


    @Autowired
    private CarAdvertisementService carAdvertisementServiceImpl;

    @GetMapping
    public ResponseEntity<?> getAllCarAds(PageableParams pageableParams) throws ValidationException {
        List<CarAdvertisementResponse> advertisements = carAdvertisementServiceImpl.findAll(false, pageableParams);
        return new ResponseEntity<>(advertisements, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCarAd(@PathVariable("id") long id, Currency currency) throws ResourceNotFoundException {
        CarAdvertisementResponse carAd = carAdvertisementServiceImpl.findAdvertisement(id, currency);
        return new ResponseEntity<>(carAd, HttpStatus.OK);
    }
}
