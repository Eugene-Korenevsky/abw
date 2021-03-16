package com.example.abw.servicies.pagination.car_ad.with_two_param.logic;

import com.example.abw.AppProperties;
import com.example.abw.entities.ad.Ad;
import com.example.abw.repositories.pagination.ad.car_ad.CarAdPaginationRepository;
import com.example.abw.servicies.pagination.car_ad.with_two_param.CarAdPagServiceWithTwoParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarAdPagBetweenPricePriceDesc implements CarAdPagServiceWithTwoParam<Long, Long> {
    @Autowired
    private AppProperties appProperties;
    @Autowired
    private CarAdPaginationRepository carAdPaginationRepository;

    @Override
    public List<Ad> getPaginationResult(Long start, Long end, int page, int size) {
        Pageable sortedByDate = PageRequest.of(page, size, Sort.by("price").descending());
        return new ArrayList<>(carAdPaginationRepository
                .readAllByPriceBetweenAndSold(start, end, false, sortedByDate));
    }

    @Override
    public List<Ad> getPaginationResultByDefault(Long start, Long end, int page) {
        Pageable sortedByDate = PageRequest.of(page, appProperties.getPageSize(),
                Sort.by("price").descending());
        return new ArrayList<>(carAdPaginationRepository
                .readAllByPriceBetweenAndSold(start, end, false, sortedByDate));
    }
}
