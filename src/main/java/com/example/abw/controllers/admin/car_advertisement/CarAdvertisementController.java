package com.example.abw.controllers.admin.car_advertisement;

import com.example.abw.entities.advertisement.Advertisement;
import com.example.abw.entities.advertisement.CarAdvertisement;
import com.example.abw.exception.validation.ValidationException;
import com.example.abw.model.advertisement.car_advertisement.CarAdvertisementResponse;
import com.example.abw.model.pageable.PageableParams;
import com.example.abw.servicies.CarAdvertisementService;
import com.example.abw.exception.entities.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("adminCarAdController")
@RequestMapping("admin/carAdvertisements")
public class CarAdvertisementController {

    @Autowired
    private CarAdvertisementService carAdvertisementServiceImpl;

    @GetMapping
    public ResponseEntity<?> getAllCarAds(PageableParams pageableParams) throws ValidationException {
        List<CarAdvertisementResponse> advertisements = carAdvertisementServiceImpl.findAll(true, pageableParams);
        return new ResponseEntity<>(advertisements, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCarAd(@PathVariable("id") long id) throws ResourceNotFoundException {
        CarAdvertisement carAd = carAdvertisementServiceImpl.findById(id);
        return new ResponseEntity<>(carAd, HttpStatus.OK);
    }
}
