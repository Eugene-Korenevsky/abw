package com.example.abw.servicies.pagination.car_ad.with_two_param;

import com.example.abw.AppProperties;
import com.example.abw.entities.ad.Ad;
import com.example.abw.entities.ad.CarAd;
import com.example.abw.repositories.pagination.ad.car_ad.CarAdPaginationRepository;
import com.example.abw.servicies.pagination.car_ad.SortKind;
import com.example.abw.servicies.pagination.car_ad.with_two_param.logic.CarAdPagBetweenPrice;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;

public class CarAdPagBetweenPriceTest {
    @Mock
    AppProperties appProperties;
    @Mock
    CarAdPaginationRepository carAdPaginationRepository;

    @InjectMocks
    CarAdPagBetweenPrice carAdPagBetweenPrice = new CarAdPagBetweenPrice();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void serviceCheck() {
        CarAd ad = new CarAd();
        ad.setPrice(500000L);
        CarAd ad1 = new CarAd();
        ad1.setPrice(530000L);
        CarAd ad2 = new CarAd();
        ad2.setPrice(550000L);
        CarAd ad3 = new CarAd();
        ad3.setPrice(650000L);
        long start = 450000L;
        long end = 750000L;
        int page = 0;
        int size = 30;
        String filter = "price";
        ArrayList<CarAd> ads = new ArrayList<>();
        ads.add(ad);
        ads.add(ad1);
        ads.add(ad2);
        ads.add(ad3);
        Pageable pageable = PageRequest.of(page, size, Sort.by(filter).ascending());
        when(carAdPaginationRepository.readAllByPriceBetween(start, end, pageable)).thenReturn(ads);
        List<Ad> ads1 = carAdPagBetweenPrice.getPaginationResult(start, end, page,
                size, filter, SortKind.ASC, true);
        List<Ad> ads2 = carAdPagBetweenPrice.getPaginationResult(start, end, page,
                size, filter, SortKind.ASC, false);
        assertEquals(4, ads1.size());
        assertEquals(0, ads2.size());

    }
}
