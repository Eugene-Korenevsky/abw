package com.example.abw.controllers.admin.car_ad;

import com.example.abw.entities.ad.Ad;
import com.example.abw.entities.ad.CarAd;
import com.example.abw.model.pageable.PageableParams;
import com.example.abw.servicies.CarAdService;
import com.example.abw.servicies.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("adminCarAdController")
@RequestMapping("admin/carAds")
public class CarAdController {

    @Autowired
    private CarAdService carAdServiceImpl;

    @GetMapping
    public ResponseEntity<?> getAllCarAds(PageableParams pageableParams) {
        List<Ad> ads = carAdServiceImpl.findAll(true, pageableParams);
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCarAd(@PathVariable("id") long id) throws ResourceNotFoundException {
        CarAd carAd = carAdServiceImpl.findById(id);
        return new ResponseEntity<>(carAd, HttpStatus.OK);
    }
}
