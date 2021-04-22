package com.example.abw.controllers.admin.car_advertisement.search;

import com.example.abw.AppProperties;
import com.example.abw.entities.advertisement.Advertisement;
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

@RestController("adminByOneStringController")
@RequestMapping("/admin/carAds")
public class ByOneStringController {
    @Autowired
    private AppProperties appProperties;
    @Autowired
    private CarAdvertisementService carAdvertisementServiceImpl;

    @GetMapping("/carBrand")
    public ResponseEntity<?> getAllByCarBrand(PageableParams pageableParams,
                                              @RequestParam(value = "carBrand") String carBrand) {
        List<Advertisement> advertisements = carAdvertisementServiceImpl.findAllByCarBrand(carBrand, true, pageableParams);
        return new ResponseEntity<>(advertisements, HttpStatus.OK);
    }

    @GetMapping("/carBrandName")
    public ResponseEntity<?> getAllByCarBrandName(PageableParams pageableParams,
                                                  @RequestParam(value = "carBrandName") String carBrandName) {
        List<Advertisement> advertisements = carAdvertisementServiceImpl.findAllByCarBrandName(carBrandName, false, pageableParams);
        return new ResponseEntity<>(advertisements, HttpStatus.OK);
    }
}
