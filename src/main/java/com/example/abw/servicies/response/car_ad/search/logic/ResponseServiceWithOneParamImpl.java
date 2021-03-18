package com.example.abw.servicies.response.car_ad.search.logic;

import com.example.abw.AppProperties;
import com.example.abw.entities.ad.Ad;
import com.example.abw.servicies.pagination.car_ad.SortKind;
import com.example.abw.servicies.pagination.car_ad.with_one_param.CarAdPagServiceWithOneParam;
import com.example.abw.servicies.response.car_ad.search.ResponseServiceWithOneParam;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseServiceWithOneParamImpl implements ResponseServiceWithOneParam {
    @Override
    public ResponseEntity<?> getResponseEntity(String filter, Integer size,
                                               String carBrand, Integer page, SortKind sortKind,
                                               CarAdPagServiceWithOneParam carAdPagServiceWithOneParam,
                                               AppProperties appProperties) {
        List<Ad> ads;
        if (filter == null) {
            if (size != null) {
                ads = carAdPagServiceWithOneParam.getPaginationResult(carBrand, page, size,
                        appProperties.getDefaultFilter(), sortKind);
            } else {
                ads = carAdPagServiceWithOneParam.getPaginationResultByDefault(carBrand, page,
                        appProperties.getDefaultFilter(), sortKind);
            }
        } else {
            try {
                if (size != null) {
                    ads = carAdPagServiceWithOneParam.getPaginationResult(carBrand, page, size,
                            filter, sortKind);
                } else {
                    ads = carAdPagServiceWithOneParam.getPaginationResultByDefault(carBrand, page,
                            filter, sortKind);
                }
            } catch (PropertyReferenceException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }
}
