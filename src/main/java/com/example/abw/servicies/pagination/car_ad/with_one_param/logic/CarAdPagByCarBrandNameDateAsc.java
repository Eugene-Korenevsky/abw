package com.example.abw.servicies.pagination.car_ad.with_one_param.logic;

import com.example.abw.AppProperties;
import com.example.abw.entities.ad.Ad;
import com.example.abw.repositories.pagination.ad.car_ad.CarAdPaginationRepository;
import com.example.abw.servicies.pagination.car_ad.with_one_param.CarAdPagServiceWithOneParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarAdPagByCarBrandNameDateAsc implements CarAdPagServiceWithOneParam<String> {
    @Autowired
    private AppProperties appProperties;
    @Autowired
    private CarAdPaginationRepository carAdPaginationRepository;

    @Override
    public List<Ad> getPaginationResultByDefault(String name, int page) {
        Pageable sortedByDate = PageRequest.of(page, appProperties.getPageSize(),
                Sort.by("publicationDate").ascending());
        return new ArrayList<>(carAdPaginationRepository
                .findByCarBrand_CarBrandName_NameAndSold(name, false, sortedByDate));
    }

    @Override
    public List<Ad> getPaginationResult(String name, int page, int size) {
        Pageable sortedByDate = PageRequest.of(page, size, Sort.by("publicationDate").ascending());
        return new ArrayList<>(carAdPaginationRepository
                .findByCarBrand_CarBrandName_NameAndSold(name, false, sortedByDate));
    }
}
