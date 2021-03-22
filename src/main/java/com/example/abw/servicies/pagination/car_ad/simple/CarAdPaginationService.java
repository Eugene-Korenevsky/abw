package com.example.abw.servicies.pagination.car_ad.simple;

import com.example.abw.entities.ad.Ad;
import com.example.abw.entities.ad.CarAd;

import java.util.List;

public interface CarAdPaginationService {
    public List<Ad> getPaginationResultByDefault(int page);

    public List<Ad> getPaginationResult(int page, int size);

    public List<Ad> getAdminPaginationResult(int page, int size);

    public List<Ad> getAdminPaginationResultByDefault(int page);
}
