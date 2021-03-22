package com.example.abw.servicies.pagination.car_ad.simple.logic;

import com.example.abw.AppProperties;
import com.example.abw.entities.ad.Ad;
import com.example.abw.entities.ad.CarAd;
import com.example.abw.entities.user.User;
import com.example.abw.repositories.pagination.ad.car_ad.CarAdPaginationRepository;
import com.example.abw.servicies.pagination.car_ad.simple.CarAdPaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class DefaultCarAdPagService implements CarAdPaginationService {
    @Autowired
    private AppProperties appProperties;
    @Autowired
    private CarAdPaginationRepository carAdPaginationRepository;


    @Override
    public List<Ad> getPaginationResult(int page, int size) {
        Pageable sortedByDate = PageRequest.of(page, size, Sort.by("publicationDate").descending());
        return new ArrayList<>(carAdPaginationRepository.readAllBySold(false, sortedByDate));
    }

    @Override
    public List<Ad> getPaginationResultByDefault(int page) {
        Pageable sortedByDate = PageRequest.of(page, appProperties.getPageSize(), Sort.by("publicationDate").descending());
        return new ArrayList<>(carAdPaginationRepository.readAllBySold(false, sortedByDate));
    }

    @Override
    public List<Ad> getAdminPaginationResult(int page, int size) {
        Pageable sortedByDate = PageRequest.of(page, size, Sort.by("publicationDate").descending());
        return getResult(sortedByDate);
    }

    @Override
    public List<Ad> getAdminPaginationResultByDefault(int page) {
        Pageable sortedByDate = PageRequest.of(page, appProperties.getPageSize(), Sort.by("publicationDate").descending());
        return getResult(sortedByDate);
    }

    private List<Ad> getResult(Pageable pageable) {
        List<Ad> result = new ArrayList<>();
        Iterable<CarAd> iterable = carAdPaginationRepository.findAll(pageable);
        Iterator<CarAd> iterator = iterable.iterator();
        if (iterator.hasNext()) iterator.forEachRemaining(result::add);
        return result;
    }
}
