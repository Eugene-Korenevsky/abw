package com.example.abw.repositories.pagination.ad.car_ad;

import com.example.abw.entities.ad.CarAd;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarAdPaginationRepository extends PagingAndSortingRepository<CarAd, Long> {
    List<CarAd> readAllBySold(boolean sold, Pageable pageable);

    public List<CarAd> findByCarBrand_CarBrandName_NameAndSold(String name,boolean sold, Pageable pageable);
}
