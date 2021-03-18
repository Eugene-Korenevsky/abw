package com.example.abw.controllers.admin.car_ad;

import com.example.abw.entities.ad.CarAd;
import com.example.abw.servicies.CarAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin/carAds")
public class CarAdController {
    @Autowired
    private CarAdService carAdService;

    @GetMapping
    public ResponseEntity<?> getAllCarAds() {
        List<CarAd> ads = carAdService.findAll();
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }
}
