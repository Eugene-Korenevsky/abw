package com.example.abw.servicies.response.car_ad.search;

import com.example.abw.AppProperties;
import com.example.abw.entities.ad.Ad;
import com.example.abw.servicies.pagination.car_ad.SortKind;
import com.example.abw.servicies.pagination.car_ad.with_three_param.CarAdPagServiceWithThreeParam;
import com.example.abw.servicies.pagination.car_ad.with_two_param.CarAdPagServiceWithTwoParam;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public abstract class ResponseServiceWithThreeParam<T, T1, T2> {
    public ResponseEntity<?> getResponseEntity(String filter, Integer size, T param, T1 param1, T2 param2, Integer page,
                                               SortKind sortKind,
                                               CarAdPagServiceWithThreeParam carAdPagServiceWithThreeParam,
                                               AppProperties appProperties) {
        List<Ad> ads;
        if (filter == null) {
            if (size != null) {
                ads = carAdPagServiceWithThreeParam.getPaginationResult(param, param1, param2, page, size,
                        appProperties.getDefaultFilter(), sortKind);
            } else {
                ads = carAdPagServiceWithThreeParam.getPaginationResultByDefault(param, param1, param2, page,
                        appProperties.getDefaultFilter(), sortKind);
            }
        } else {
            try {
                if (size != null) {
                    ads = carAdPagServiceWithThreeParam.getPaginationResult(param, param1, param2, page, size,
                            filter, sortKind);
                } else {
                    ads = carAdPagServiceWithThreeParam.getPaginationResultByDefault(param, param1, param2, page,
                            filter, sortKind);
                }
            } catch (PropertyReferenceException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }
}
