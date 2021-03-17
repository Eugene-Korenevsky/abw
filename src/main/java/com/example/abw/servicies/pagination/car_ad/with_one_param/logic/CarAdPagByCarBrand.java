package com.example.abw.servicies.pagination.car_ad.with_one_param.logic;

import com.example.abw.AppProperties;
import com.example.abw.entities.ad.Ad;
import com.example.abw.repositories.pagination.ad.car_ad.CarAdPaginationRepository;
import com.example.abw.servicies.pagination.car_ad.SortKind;
import com.example.abw.servicies.pagination.car_ad.with_one_param.CarAdPagServiceWithOneParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarAdPagByCarBrand implements CarAdPagServiceWithOneParam<String> {
    @Autowired
    private AppProperties appProperties;
    @Autowired
    private CarAdPaginationRepository carAdPaginationRepository;

    @Override
    public List<Ad> getPaginationResult(String carBrand, int page, int size, String filter, SortKind sortKind) {
        Pageable pageable;
        if (sortKind == SortKind.ASC) {
            pageable = PageRequest.of(page, size, Sort.by(filter).ascending());
        } else {
            pageable = PageRequest.of(page, size, Sort.by(filter).descending());
        }
        return new ArrayList<>(carAdPaginationRepository
                .findByCarBrand_NameAndSold(carBrand, false, pageable));
    }

    @Override
    public List<Ad> getPaginationResultByDefault(String carBrand, int page, String filter, SortKind sortKind) {
        Pageable pageable;
        if (sortKind == SortKind.ASC) {
            pageable = PageRequest.of(page, appProperties.getPageSize(), Sort.by(filter).ascending());
        } else {
            pageable = PageRequest.of(page, appProperties.getPageSize(), Sort.by(filter).descending());
        }
        return new ArrayList<>(carAdPaginationRepository
                .findByCarBrand_NameAndSold(carBrand, false, pageable));
    }
}
