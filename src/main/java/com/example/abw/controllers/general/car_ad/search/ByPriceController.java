package com.example.abw.controllers.general.car_ad.search;


import com.example.abw.entities.ad.Ad;
import com.example.abw.model.pageable.PageableParams;
import com.example.abw.servicies.CarAdService;
import com.example.abw.utils.pageable_params.PageableParamsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/carAds/price")
public class ByPriceController {
    @Autowired
    private PageableParamsUtil pageableParamsUtilImpl;
    @Autowired
    private CarAdService carAdServiceImpl;

    @GetMapping
    public ResponseEntity<?> getAllByPrice(@RequestParam(value = "page") Integer page,
                                           @RequestParam(value = "size", required = false) Integer size,
                                           @RequestParam(value = "filter", required = false) String filter,
                                           @RequestParam(value = "type", required = false) String sortKind,
                                           @RequestParam(value = "startPrice") Long startPrice,
                                           @RequestParam(value = "endPrice") Long endPrice) {
        PageableParams pageableParams = pageableParamsUtilImpl.getPageableParams(page, size, filter, sortKind);
        try {
            List<Ad> ads = carAdServiceImpl.findAllByPrice(startPrice, endPrice, false, pageableParams);
            for (Ad carAd : ads) {
                System.out.println(carAd.getSellItem().getFullName() + " " + carAd.getPublicationDate() + " " +
                        carAd.getId() + " sold " + carAd.isSold());
            }
            return new ResponseEntity<>(ads, HttpStatus.OK);
        } catch (PropertyReferenceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/carBrand")
    public ResponseEntity<?> getAllByPriceAndCarBrand(@RequestParam(value = "page") Integer page,
                                                      @RequestParam(value = "size", required = false) Integer size,
                                                      @RequestParam(value = "filter", required = false) String filter,
                                                      @RequestParam(value = "type", required = false) String sortKind,
                                                      @RequestParam(value = "startPrice") Long startPrice,
                                                      @RequestParam(value = "endPrice") Long endPrice,
                                                      @RequestParam(value = "carBrand") String carBrand) {
        PageableParams pageableParams = pageableParamsUtilImpl.getPageableParams(page, size, filter, sortKind);
        try {
            List<Ad> ads = carAdServiceImpl.
                    findAllByPriceAndCarBrand(startPrice, endPrice, carBrand, false, pageableParams);
            for (Ad carAd : ads) {
                System.out.println(carAd.getSellItem().getFullName() + " " + carAd.getPublicationDate() + " " +
                        carAd.getId() + " sold " + carAd.isSold());
            }
            return new ResponseEntity<>(ads, HttpStatus.OK);
        } catch (PropertyReferenceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/carBrandName")
    public ResponseEntity<?> getAllByPriceAndCarBrandName(@RequestParam(value = "page") Integer page,
                                                          @RequestParam(value = "size", required = false) Integer size,
                                                          @RequestParam(value = "filter", required = false) String filter,
                                                          @RequestParam(value = "type", required = false) String sortKind,
                                                          @RequestParam(value = "startPrice") Long startPrice,
                                                          @RequestParam(value = "endPrice") Long endPrice,
                                                          @RequestParam(value = "carBrandName") String carBrandName) {
        PageableParams pageableParams = pageableParamsUtilImpl.getPageableParams(page, size, filter, sortKind);
        try {
            List<Ad> ads = carAdServiceImpl.findAllByPriceAndCarBrandName(startPrice, endPrice,
                    carBrandName, false, pageableParams);
            for (Ad carAd : ads) {
                System.out.println(carAd.getSellItem().getFullName() + " " + carAd.getPublicationDate() + " " +
                        carAd.getId() + " sold " + carAd.isSold());
            }
            return new ResponseEntity<>(ads, HttpStatus.OK);
        } catch (PropertyReferenceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
