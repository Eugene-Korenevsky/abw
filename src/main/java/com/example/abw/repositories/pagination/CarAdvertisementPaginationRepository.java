package com.example.abw.repositories.pagination;

import com.example.abw.entities.advertisement.CarAdvertisement;
import com.example.abw.entities.user.User;
import com.example.abw.model.advertisement.Status;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarAdvertisementPaginationRepository extends PagingAndSortingRepository<CarAdvertisement, Long> {

    List<CarAdvertisement> readAllByUser(User user, Pageable pageable);

    public List<CarAdvertisement> readAllByStatus(Status status);

    public List<CarAdvertisement> findByCarBrand_CarBrandName_NameAndStatus(String name, Status status, Pageable pageable);

    public List<CarAdvertisement> findByCarBrand_CarBrandName_Name(String name, Pageable pageable);

    public List<CarAdvertisement> findByCarBrand_NameAndStatus(String name, Status status, Pageable pageable);

    public List<CarAdvertisement> findByCarBrand_Name(String name, Pageable pageable);

    List<CarAdvertisement> readAllByPriceBetweenAndStatus(long start, long end, Status status, Pageable pageable);

    List<CarAdvertisement> readAllByPriceBetween(long start, long end, Pageable pageable);

    List<CarAdvertisement> readAllByPriceBetweenAndStatusAndCarBrand_Name(long start, long end,
                                                                          Status status, String name, Pageable pageable);

    List<CarAdvertisement> readAllByPriceBetweenAndCarBrand_Name(long start, long end, String name, Pageable pageable);

    List<CarAdvertisement> readAllByPriceBetweenAndStatusAndCarBrand_CarBrandName_Name(long start, long end,
                                                                                       Status status, String name,
                                                                                       Pageable pageable);

    List<CarAdvertisement> readAllByPriceBetweenAndCarBrand_CarBrandName_Name(
            long start, long end, String name, Pageable pageable);
}
