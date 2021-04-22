package com.example.abw.controllers.general.car_advertisement;

import com.example.abw.entities.advertisement.Advertisement;
import com.example.abw.entities.advertisement.CarAdvertisement;
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
    public ResponseEntity<?> getAllCarAds(PageableParams pageableParams) {
        List<Advertisement> advertisements = carAdvertisementServiceImpl.findAll(false, pageableParams);
        return new ResponseEntity<>(advertisements, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCarAd(@PathVariable("id") long id) throws ResourceNotFoundException {
        CarAdvertisement carAd = carAdvertisementServiceImpl.findAdvertisement(id);
        return new ResponseEntity<>(carAd, HttpStatus.OK);
    }
}
