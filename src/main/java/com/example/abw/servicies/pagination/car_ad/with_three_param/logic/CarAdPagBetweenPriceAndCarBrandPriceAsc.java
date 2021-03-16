package com.example.abw.servicies.pagination.car_ad.with_three_param.logic;

import com.example.abw.AppProperties;
import com.example.abw.entities.ad.Ad;
import com.example.abw.repositories.pagination.ad.car_ad.CarAdPaginationRepository;
import com.example.abw.servicies.pagination.car_ad.with_three_param.CarAdPagServiceWithThreeParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarAdPagBetweenPriceAndCarBrandPriceAsc implements
        CarAdPagServiceWithThreeParam<Long, Long, String> {
    @Autowired
    private AppProperties appProperties;
    @Autowired
    private CarAdPaginationRepository carAdPaginationRepository;

    @Override
    public List<Ad> getPaginationResult(Long start, Long end, String carBrandName, int page, int size) {
        Pageable sortedByDate = PageRequest.of(page, size, Sort.by("price").ascending());
        return new ArrayList<>(carAdPaginationRepository
                .readAllByPriceBetweenAndSoldAndCarBrand_Name(start, end, false, carBrandName, sortedByDate));
    }

    @Override
    public List<Ad> getPaginationResultByDefault(Long start, Long end, String carBrandName, int page) {
        Pageable sortedByDate = PageRequest.of(page, appProperties.getPageSize(),
                Sort.by("price").ascending());
        return new ArrayList<>(carAdPaginationRepository
                .readAllByPriceBetweenAndSoldAndCarBrand_Name(start, end, false, carBrandName, sortedByDate));
    }
}
