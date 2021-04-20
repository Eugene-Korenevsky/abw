package com.example.abw.controllers.admin.car_ad.search;

import com.example.abw.AppProperties;
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

@RestController("adminByOneStringController")
@RequestMapping("/admin/carAds")
public class ByOneStringController {
    @Autowired
    private AppProperties appProperties;
    @Autowired
    private CarAdService carAdServiceImpl;

    @GetMapping("/carBrand")
    public ResponseEntity<?> getAllByCarBrand(PageableParams pageableParams,
                                              @RequestParam(value = "carBrand") String carBrand) {
        List<Ad> ads = carAdServiceImpl.findAllByCarBrand(carBrand, true, pageableParams);
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @GetMapping("/carBrandName")
    public ResponseEntity<?> getAllByCarBrandName(PageableParams pageableParams,
                                                  @RequestParam(value = "carBrandName") String carBrandName) {
        List<Ad> ads = carAdServiceImpl.findAllByCarBrandName(carBrandName, false, pageableParams);
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }
}
