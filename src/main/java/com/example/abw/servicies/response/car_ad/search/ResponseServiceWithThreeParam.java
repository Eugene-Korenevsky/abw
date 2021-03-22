package com.example.abw.servicies.response.car_ad.search;

import com.example.abw.AppProperties;
import com.example.abw.entities.ad.Ad;
import com.example.abw.servicies.pagination.car_ad.SortKind;
import com.example.abw.servicies.pagination.car_ad.with_three_param.CarAdPagServiceWithThreeParam;
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
                        appProperties.getDefaultFilter(), sortKind, isAdmin());
            } else {
                ads = carAdPagServiceWithThreeParam.getPaginationResultByDefault(param, param1, param2, page,
                        appProperties.getDefaultFilter(), sortKind, isAdmin());
            }
        } else {
            try {
                if (size != null) {
                    ads = carAdPagServiceWithThreeParam.getPaginationResult(param, param1, param2, page, size,
                            filter, sortKind, isAdmin());
                } else {
                    ads = carAdPagServiceWithThreeParam.getPaginationResultByDefault(param, param1, param2, page,
                            filter, sortKind, isAdmin());
                }
            } catch (PropertyReferenceException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        for (Ad ad : ads) {
            System.out.println(ad.getPublicationDate() + " " + ad.getSellItem().getFullName() + " " + ad.getPrice()
                    + " isSold" + ad.isSold());
        }
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    public abstract boolean isAdmin();
}
