package com.example.abw.controllers.general.car_ad.search;


import com.example.abw.AppProperties;
import com.example.abw.servicies.pagination.car_ad.SortKind;
import com.example.abw.servicies.pagination.car_ad.with_three_param.CarAdPagServiceWithThreeParam;
import com.example.abw.servicies.pagination.car_ad.with_two_param.CarAdPagServiceWithTwoParam;
import com.example.abw.servicies.response.car_ad.search.ResponseServiceWithThreeParam;
import com.example.abw.servicies.response.car_ad.search.ResponseServiceWithTwoParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carAds/price")
public class ByPriceController {
    @Autowired
    private AppProperties appProperties;
    @Autowired
    private CarAdPagServiceWithTwoParam carAdPagBetweenPrice;
    @Autowired
    private CarAdPagServiceWithThreeParam carAdPagBetweenPriceAndCarBrand;
    @Autowired
    private CarAdPagServiceWithThreeParam carAdPagBetweenPriceAndCarBrandName;
    @Autowired
    private ResponseServiceWithTwoParam responseServiceByPrice;
    @Autowired
    private ResponseServiceWithThreeParam responseServiceByPriceAndString;

    @GetMapping
    public ResponseEntity<?> getAllByPrice(@RequestParam(value = "page") Integer page,
                                           @RequestParam(value = "size", required = false) Integer size,
                                           @RequestParam(value = "filter", required = false) String filter,
                                           @RequestParam(value = "type", required = false) String type,
                                           @RequestParam(value = "startPrice") Long startPrice,
                                           @RequestParam(value = "endPrice") Long endPrice) {
        if (type != null) {
            if (type.equals("asc")) {
                return responseServiceByPrice.getResponseEntity(
                        filter, size, startPrice, endPrice, page, SortKind.ASC, carAdPagBetweenPrice, appProperties);
            } else if (type.equals("desc")) {
                return responseServiceByPrice.getResponseEntity(
                        filter, size, startPrice, endPrice, page, SortKind.DESC, carAdPagBetweenPrice, appProperties);
            } else {
                return responseServiceByPrice.getResponseEntity(
                        filter, size, startPrice, endPrice, page, SortKind.DESC, carAdPagBetweenPrice, appProperties);
            }
        } else {
            return responseServiceByPrice.getResponseEntity(
                    filter, size, startPrice, endPrice, page, SortKind.DESC, carAdPagBetweenPrice, appProperties);
        }
    }

    @GetMapping("/carBrand")
    public ResponseEntity<?> getAllByPriceAndCarBrand(@RequestParam(value = "page") Integer page,
                                                      @RequestParam(value = "size", required = false) Integer size,
                                                      @RequestParam(value = "filter", required = false) String filter,
                                                      @RequestParam(value = "type", required = false) String type,
                                                      @RequestParam(value = "startPrice") Long startPrice,
                                                      @RequestParam(value = "endPrice") Long endPrice,
                                                      @RequestParam(value = "carBrand") String carBrand) {
        if (type != null) {
            if (type.equals("asc")) {
                return responseServiceByPriceAndString.getResponseEntity(
                        filter, size, startPrice, endPrice, carBrand, page,
                        SortKind.ASC, carAdPagBetweenPriceAndCarBrand, appProperties);
            } else if (type.equals("desc")) {
                return responseServiceByPriceAndString.getResponseEntity(
                        filter, size, startPrice, endPrice, carBrand, page,
                        SortKind.DESC, carAdPagBetweenPriceAndCarBrand, appProperties);
            } else {
                return responseServiceByPriceAndString.getResponseEntity(
                        filter, size, startPrice, endPrice, carBrand, page,
                        SortKind.DESC, carAdPagBetweenPriceAndCarBrand, appProperties);
            }
        } else {
            return responseServiceByPriceAndString.getResponseEntity(
                    filter, size, startPrice, endPrice, carBrand, page,
                    SortKind.DESC, carAdPagBetweenPriceAndCarBrand, appProperties);
        }
    }

    @GetMapping("/carBrandName")
    public ResponseEntity<?> getAllByPriceAndCarBrandName(@RequestParam(value = "page") Integer page,
                                                          @RequestParam(value = "size", required = false) Integer size,
                                                          @RequestParam(value = "filter", required = false) String filter,
                                                          @RequestParam(value = "type", required = false) String type,
                                                          @RequestParam(value = "startPrice") Long startPrice,
                                                          @RequestParam(value = "endPrice") Long endPrice,
                                                          @RequestParam(value = "carBrandName") String carBrandName) {
        if (type != null) {
            if (type.equals("asc")) {
                return responseServiceByPriceAndString.getResponseEntity(
                        filter, size, startPrice, endPrice, carBrandName, page,
                        SortKind.ASC, carAdPagBetweenPriceAndCarBrandName, appProperties);
            } else if (type.equals("desc")) {
                return responseServiceByPriceAndString.getResponseEntity(
                        filter, size, startPrice, endPrice, carBrandName, page,
                        SortKind.DESC, carAdPagBetweenPriceAndCarBrandName, appProperties);
            } else {
                return responseServiceByPriceAndString.getResponseEntity(
                        filter, size, startPrice, endPrice, carBrandName, page,
                        SortKind.DESC, carAdPagBetweenPriceAndCarBrandName, appProperties);
            }
        } else {
            return responseServiceByPriceAndString.getResponseEntity(
                    filter, size, startPrice, endPrice, carBrandName, page,
                    SortKind.DESC, carAdPagBetweenPriceAndCarBrandName, appProperties);
        }
    }
}
