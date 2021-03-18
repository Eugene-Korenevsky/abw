package com.example.abw.servicies.response.car_ad.search;

import com.example.abw.AppProperties;
import com.example.abw.servicies.pagination.car_ad.SortKind;
import com.example.abw.servicies.pagination.car_ad.with_one_param.CarAdPagServiceWithOneParam;
import org.springframework.http.ResponseEntity;

public interface ResponseServiceWithOneParam {
    public ResponseEntity<?> getResponseEntity(String filter, Integer size, String carBrand, Integer page,
                                               SortKind sortKind,
                                               CarAdPagServiceWithOneParam carAdPagServiceWithOneParam,
                                               AppProperties appProperties);
}
