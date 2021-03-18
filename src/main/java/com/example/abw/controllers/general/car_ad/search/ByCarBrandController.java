package com.example.abw.controllers.general.car_ad.search;

import com.example.abw.AppProperties;
import com.example.abw.servicies.pagination.car_ad.SortKind;
import com.example.abw.servicies.pagination.car_ad.with_one_param.CarAdPagServiceWithOneParam;
import com.example.abw.servicies.response.car_ad.search.ResponseServiceWithOneParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carAds")
public class ByCarBrandController {
    @Autowired
    CarAdPagServiceWithOneParam carAdPagByCarBrand;
    @Autowired
    CarAdPagServiceWithOneParam carAdPagByCarBrandName;
    @Autowired
    private AppProperties appProperties;
    @Autowired
    private ResponseServiceWithOneParam responseServiceWithOneParamString;

    @GetMapping("/carBrand")
    public ResponseEntity<?> getAllByCarBrand(@RequestParam(value = "page") Integer page,
                                              @RequestParam(value = "size", required = false) Integer size,
                                              @RequestParam(value = "filter", required = false) String filter,
                                              @RequestParam(value = "type", required = false) String type,
                                              @RequestParam(value = "carBrand") String carBrand) {
        if (type != null) {
            if (type.equals("asc")) {
                return responseServiceWithOneParamString.getResponseEntity(
                        filter, size, carBrand, page, SortKind.ASC, carAdPagByCarBrand, appProperties);
            } else if (type.equals("desc")) {
                return responseServiceWithOneParamString.getResponseEntity(
                        filter, size, carBrand, page, SortKind.DESC, carAdPagByCarBrand, appProperties);
            } else {
                return responseServiceWithOneParamString.getResponseEntity(
                        filter, size, carBrand, page, SortKind.DESC, carAdPagByCarBrand, appProperties);
            }
        } else {
            return responseServiceWithOneParamString.getResponseEntity(
                    filter, size, carBrand, page, SortKind.DESC, carAdPagByCarBrand, appProperties);
        }
    }

    @GetMapping("/carBrandName")
    public ResponseEntity<?> getAllByCarBrandName(@RequestParam(value = "page") Integer page,
                                                  @RequestParam(value = "size", required = false) Integer size,
                                                  @RequestParam(value = "filter", required = false) String filter,
                                                  @RequestParam(value = "type", required = false) String type,
                                                  @RequestParam(value = "carBrandName") String carBrandName) {
        if (type != null) {
            if (type.equals("asc")) {
                return responseServiceWithOneParamString.getResponseEntity(
                        filter, size, carBrandName, page, SortKind.ASC, carAdPagByCarBrandName, appProperties);
            } else if (type.equals("desc")) {
                return responseServiceWithOneParamString.getResponseEntity(
                        filter, size, carBrandName, page, SortKind.DESC, carAdPagByCarBrandName, appProperties);
            } else {
                return responseServiceWithOneParamString.getResponseEntity(
                        filter, size, carBrandName, page, SortKind.DESC, carAdPagByCarBrandName, appProperties);
            }
        } else {
            return responseServiceWithOneParamString.getResponseEntity(
                    filter, size, carBrandName, page, SortKind.DESC, carAdPagByCarBrandName, appProperties);
        }
    }
}
