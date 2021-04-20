package com.example.abw.controllers.admin.car_ad.search;

import com.example.abw.entities.ad.Ad;
import com.example.abw.model.pageable.PageableParams;
import com.example.abw.servicies.CarAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("adminByPriceController")
@RequestMapping("admin/carAds/price")
public class ByPriceController {
    @Autowired
    private CarAdService carAdServiceImpl;

    @GetMapping
    public ResponseEntity<?> getAllByPrice(PageableParams pageableParams,
                                           @RequestParam(value = "startPrice") Long startPrice,
                                           @RequestParam(value = "endPrice") Long endPrice) {
        List<Ad> ads = carAdServiceImpl.findAllByPrice(startPrice, endPrice, true, pageableParams);
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @GetMapping("/carBrand")
    public ResponseEntity<?> getAllByPriceAndCarBrand(PageableParams pageableParams,
                                                      @RequestParam(value = "startPrice") Long startPrice,
                                                      @RequestParam(value = "endPrice") Long endPrice,
                                                      @RequestParam(value = "carBrand") String carBrand) {
        List<Ad> ads = carAdServiceImpl.findAllByPriceAndCarBrand(startPrice, endPrice,
                carBrand, true, pageableParams);
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @GetMapping("/carBrandName")
    public ResponseEntity<?> getAllByPriceAndCarBrandName(PageableParams pageableParams,
                                                          @RequestParam(value = "startPrice") Long startPrice,
                                                          @RequestParam(value = "endPrice") Long endPrice,
                                                          @RequestParam(value = "carBrandName") String carBrandName) {
        List<Ad> ads = carAdServiceImpl.findAllByPriceAndCarBrandName(startPrice, endPrice,
                carBrandName, true, pageableParams);
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }
}
