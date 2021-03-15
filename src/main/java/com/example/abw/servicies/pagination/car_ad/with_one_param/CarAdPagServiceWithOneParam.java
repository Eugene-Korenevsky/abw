package com.example.abw.servicies.pagination.car_ad.with_one_param;

import com.example.abw.entities.ad.Ad;
import com.example.abw.entities.ad.CarAd;

import java.util.List;

public interface CarAdPagServiceWithOneParam<T> {
    public List<Ad> getPaginationResult(T param, int page, int size);

    public List<Ad> getPaginationResultByDefault(T param, int page);
}
