package com.example.abw.controllers.general.car_ad;

import com.example.abw.entities.ad.Ad;
import com.example.abw.entities.ad.CarAd;
import com.example.abw.model.pageable.PageableParams;
import com.example.abw.servicies.CarAdService;
import com.example.abw.servicies.exceptions.ResourceNotFoundException;
import com.example.abw.utils.pageable_params.PageableParamsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/carAds", produces = "application/json")
public class CarAdController {


    @Autowired
    private CarAdService carAdServiceImpl;
    @Autowired
    private PageableParamsUtil pageableParamsUtilImpl;

    @GetMapping
    public ResponseEntity<?> getAllCarAds(@RequestParam(value = "page", required = false) Integer page,
                                          @RequestParam(value = "size", required = false) Integer size) {
        PageableParams pageableParams = pageableParamsUtilImpl.getPageableParams(page, size, null, null);
        try {
            List<Ad> ads = carAdServiceImpl.findAll(false, pageableParams);
            return new ResponseEntity<>(ads, HttpStatus.OK);
        } catch (PropertyReferenceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
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
