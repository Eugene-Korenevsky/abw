package com.example.abw.repositories.pagination.advertisement.car_advertisement;

import com.example.abw.entities.advertisement.CarAdvertisement;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarAdvertisementPaginationRepository extends PagingAndSortingRepository<CarAdvertisement, Long> {
    List<CarAdvertisement> readAllBySold(boolean sold, Pageable pageable);

    public List<CarAdvertisement> findByCarBrand_CarBrandName_NameAndSold(String name, boolean sold, Pageable pageable);

    public List<CarAdvertisement> findByCarBrand_CarBrandName_Name(String name, Pageable pageable);

    public List<CarAdvertisement> findByCarBrand_NameAndSold(String name, boolean sold, Pageable pageable);

    public List<CarAdvertisement> findByCarBrand_Name(String name, Pageable pageable);

    List<CarAdvertisement> readAllByPriceBetweenAndSold(long start, long end, boolean sold, Pageable pageable);

    List<CarAdvertisement> readAllByPriceBetween(long start, long end, Pageable pageable);

    List<CarAdvertisement> readAllByPriceBetweenAndSoldAndCarBrand_Name(long start, long end,
                                                                        boolean sold, String name, Pageable pageable);

    List<CarAdvertisement> readAllByPriceBetweenAndCarBrand_Name(long start, long end, String name, Pageable pageable);

    List<CarAdvertisement> readAllByPriceBetweenAndSoldAndCarBrand_CarBrandName_Name(long start, long end,
                                                                                     boolean sold, String name, Pageable pageable);

    List<CarAdvertisement> readAllByPriceBetweenAndCarBrand_CarBrandName_Name(long start, long end, String name, Pageable pageable);
}
