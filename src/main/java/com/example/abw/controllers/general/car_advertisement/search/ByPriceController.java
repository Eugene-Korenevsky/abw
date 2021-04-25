package com.example.abw.controllers.general.car_advertisement.search;


import com.example.abw.entities.advertisement.Advertisement;
import com.example.abw.entities.advertisement.CarAdvertisement;
import com.example.abw.model.advertisement.car_advertisement.CarAdvertisementResponse;
import com.example.abw.model.pageable.PageableParams;
import com.example.abw.servicies.CarAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/carAdvertisements/price")
public class ByPriceController {
    @Autowired
    private CarAdvertisementService carAdvertisementServiceImpl;

    @GetMapping
    public ResponseEntity<?> getAllByPrice(PageableParams pageableParams,
                                           @RequestParam(value = "startPrice") Long startPrice,
                                           @RequestParam(value = "endPrice") Long endPrice) {
        List<CarAdvertisementResponse> advertisements =
                carAdvertisementServiceImpl.findAllByPrice(startPrice, endPrice, false, pageableParams);
        return new ResponseEntity<>(advertisements, HttpStatus.OK);
    }

    @GetMapping("/carBrand")
    public ResponseEntity<?> getAllByPriceAndCarBrand(PageableParams pageableParams,
                                                      @RequestParam(value = "startPrice") Long startPrice,
                                                      @RequestParam(value = "endPrice") Long endPrice,
                                                      @RequestParam(value = "carBrand") String carBrand) {
        List<CarAdvertisementResponse> advertisements = carAdvertisementServiceImpl.
                findAllByPriceAndCarBrand(startPrice, endPrice, carBrand, false, pageableParams);
        return new ResponseEntity<>(advertisements, HttpStatus.OK);
    }

    @GetMapping("/carBrandName")
    public ResponseEntity<?> getAllByPriceAndCarBrandName(PageableParams pageableParams,
                                                          @RequestParam(value = "startPrice") Long startPrice,
                                                          @RequestParam(value = "endPrice") Long endPrice,
                                                          @RequestParam(value = "carBrandName") String carBrandName) {
        List<CarAdvertisementResponse> advertisements =
                carAdvertisementServiceImpl.findAllByPriceAndCarBrandName(startPrice, endPrice,
                carBrandName, false, pageableParams);
        return new ResponseEntity<>(advertisements, HttpStatus.OK);
    }
}
