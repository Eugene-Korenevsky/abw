package com.example.abw.controllers.general.car_advertisement.search;

import com.example.abw.AppProperties;
import com.example.abw.entities.advertisement.Advertisement;
import com.example.abw.entities.advertisement.CarAdvertisement;
import com.example.abw.model.advertisement.car_advertisement.CarAdvertisementResponse;
import com.example.abw.model.pageable.PageableParams;
import com.example.abw.servicies.CarAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carAdvertisements")
public class ByOneStringController {
    @Autowired
    private CarAdvertisementService carAdvertisementServiceImpl;
    @Autowired
    private AppProperties appProperties;

    @GetMapping("/carBrand")
    public ResponseEntity<?> getAllByCarBrand(PageableParams pageableParams,
                                              @RequestParam(value = "carBrand") String carBrand) {
        List<CarAdvertisementResponse> advertisements =
                carAdvertisementServiceImpl.findAllByCarBrand(carBrand, false, pageableParams);
        return new ResponseEntity<>(advertisements, HttpStatus.OK);
    }

    @GetMapping("/carBrandName")
    public ResponseEntity<?> getAllByCarBrandName(PageableParams pageableParams,
                                                  @RequestParam(value = "carBrandName") String carBrandName) {
        List<CarAdvertisementResponse> advertisements =
                carAdvertisementServiceImpl.findAllByCarBrandName(carBrandName, false, pageableParams);
        return new ResponseEntity<>(advertisements, HttpStatus.OK);
    }
}
