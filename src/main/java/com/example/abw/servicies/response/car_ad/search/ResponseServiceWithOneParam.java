package com.example.abw.servicies.response.car_ad.search;

import com.example.abw.AppProperties;
import com.example.abw.entities.ad.Ad;
import com.example.abw.servicies.pagination.car_ad.SortKind;
import com.example.abw.servicies.pagination.car_ad.with_one_param.CarAdPagServiceWithOneParam;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class ResponseServiceWithOneParam<T> {
    public ResponseEntity<?> getResponseEntity(String filter, Integer size, T param, Integer page,
                                               SortKind sortKind,
                                               CarAdPagServiceWithOneParam carAdPagServiceWithOneParam,
                                               AppProperties appProperties) {
        List<Ad> ads;
        if (filter == null) {
            if (size != null) {
                ads = carAdPagServiceWithOneParam.getPaginationResult(param, page, size,
                        appProperties.getDefaultFilter(), sortKind);
            } else {
                ads = carAdPagServiceWithOneParam.getPaginationResultByDefault(param, page,
                        appProperties.getDefaultFilter(), sortKind);
            }
        } else {
            try {
                if (size != null) {
                    ads = carAdPagServiceWithOneParam.getPaginationResult(param, page, size,
                            filter, sortKind);
                } else {
                    ads = carAdPagServiceWithOneParam.getPaginationResultByDefault(param, page,
                            filter, sortKind);
                }
            } catch (PropertyReferenceException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }
}
