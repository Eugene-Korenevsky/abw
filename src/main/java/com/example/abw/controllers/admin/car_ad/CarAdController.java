package com.example.abw.controllers.admin.car_ad;

import com.example.abw.entities.ad.Ad;
import com.example.abw.entities.ad.CarAd;
import com.example.abw.servicies.CarAdService;
import com.example.abw.servicies.exceptions.ResourceNotFoundException;
import com.example.abw.servicies.pagination.car_ad.simple.CarAdPaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("adminCarAdController")
@RequestMapping("admin/carAds")
public class CarAdController {
  /*  @Autowired
    private CarAdService carAdService;*/

    @Autowired
    private CarAdService carAdServiceImpl;
    @Autowired
    private CarAdPaginationService defaultCarAdPagService;

    /* @GetMapping
     public ResponseEntity<?> getAllCarAds() {
         List<CarAd> ads = carAdService.findAll();
         return new ResponseEntity<>(ads, HttpStatus.OK);
     }*/
    @GetMapping
    public ResponseEntity<?> getAllCarAds(@RequestParam(value = "page", required = false) Integer page,
                                          @RequestParam(value = "size", required = false) Integer size) {
        if (page == null && size == null) {
            List<Ad> ads = defaultCarAdPagService.getAdminPaginationResultByDefault(0);
            return new ResponseEntity<>(ads, HttpStatus.OK);
        } else if (page == null) {
            List<Ad> ads = defaultCarAdPagService.getAdminPaginationResult(0, size);
            return new ResponseEntity<>(ads, HttpStatus.OK);
        } else if (size == null) {
            List<Ad> ads = defaultCarAdPagService.getAdminPaginationResultByDefault(page);
            return new ResponseEntity<>(ads, HttpStatus.OK);
        } else {
            List<Ad> ads = defaultCarAdPagService.getAdminPaginationResult(page, size);
            return new ResponseEntity<>(ads, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCarAd(@PathVariable("id") long id) {
        try {
            CarAd carAd = carAdServiceImpl.findById(id);
            return new ResponseEntity<>(carAd, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
