package com.example.abw.controllers.general.car_ad.search;

import com.example.abw.AppProperties;
import com.example.abw.entities.ad.Ad;
import com.example.abw.model.pageable.PageableParams;
import com.example.abw.servicies.CarAdService;
import com.example.abw.utils.pageable_params.PageableParamsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.PropertyReferenceException;
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
    @Autowired
    private PageableParamsUtil pageableParamsUtilImpl;

    @GetMapping("/carBrand")
    public ResponseEntity<?> getAllByCarBrand(@RequestParam(value = "page") Integer page,
                                              @RequestParam(value = "size", required = false) Integer size,
                                              @RequestParam(value = "filter", required = false) String filter,
                                              @RequestParam(value = "type", required = false) String sortKind,
                                              @RequestParam(value = "carBrand") String carBrand) {
        PageableParams pageableParams = pageableParamsUtilImpl.getPageableParams(page, size, filter, sortKind);
        try {
            List<Ad> ads = carAdServiceImpl.findAllByCarBrand(carBrand, false, pageableParams);
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
    public ResponseEntity<?> getAllByCarBrandName(@RequestParam(value = "page") Integer page,
                                                  @RequestParam(value = "size", required = false) Integer size,
                                                  @RequestParam(value = "filter", required = false) String filter,
                                                  @RequestParam(value = "type", required = false) String sortKind,
                                                  @RequestParam(value = "carBrandName") String carBrandName) {
        PageableParams pageableParams = pageableParamsUtilImpl.getPageableParams(page, size, filter, sortKind);
        try {
            List<Ad> ads = carAdServiceImpl.findAllByCarBrandName(carBrandName, false, pageableParams);
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
