package com.example.abw.repositories.pagination.ad.car_ad;

import com.example.abw.entities.ad.CarAd;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface CarAdPaginationRepository extends PagingAndSortingRepository<CarAd, Long> {
    List<CarAd> readAllBySold(boolean sold, Pageable pageable);

    public List<CarAd> findByCarBrand_CarBrandName_NameAndSold(String name, boolean sold, Pageable pageable);

    public List<CarAd> findByCarBrand_CarBrandName_Name(String name, Pageable pageable);

    public List<CarAd> findByCarBrand_NameAndSold(String name, boolean sold, Pageable pageable);

    public List<CarAd> findByCarBrand_Name(String name, Pageable pageable);

    List<CarAd> readAllByPriceBetweenAndSold(long start, long end, boolean sold, Pageable pageable);

    List<CarAd> readAllByPriceBetween(long start, long end, Pageable pageable);

    List<CarAd> readAllByPriceBetweenAndSoldAndCarBrand_Name(long start, long end,
                                                             boolean sold, String name, Pageable pageable);

    List<CarAd> readAllByPriceBetweenAndCarBrand_Name(long start, long end, String name, Pageable pageable);

    List<CarAd> readAllByPriceBetweenAndSoldAndCarBrand_CarBrandName_Name(long start, long end,
                                                                          boolean sold, String name, Pageable pageable);

    List<CarAd> readAllByPriceBetweenAndCarBrand_CarBrandName_Name(long start, long end, String name, Pageable pageable);
}
