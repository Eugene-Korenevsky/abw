package com.example.abw.controllers.general.car_ad.search;

import com.example.abw.AppProperties;
import com.example.abw.entities.ad.Ad;
import com.example.abw.model.pageable.PageableParams;
import com.example.abw.servicies.CarAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carAds")
public class ByOneStringController {
    @Autowired
    private CarAdService carAdServiceImpl;
    @Autowired
    private AppProperties appProperties;

    @GetMapping("/carBrand")
    public ResponseEntity<?> getAllByCarBrand(PageableParams pageableParams,
                                              @RequestParam(value = "carBrand") String carBrand) {
        List<Ad> ads = carAdServiceImpl.findAllByCarBrand(carBrand, false, pageableParams);
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @GetMapping("/carBrandName")
    public ResponseEntity<?> getAllByCarBrandName(PageableParams pageableParams,
                                                  @RequestParam(value = "carBrandName") String carBrandName) {
        List<Ad> ads = carAdServiceImpl.findAllByCarBrandName(carBrandName, false, pageableParams);
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }
}
