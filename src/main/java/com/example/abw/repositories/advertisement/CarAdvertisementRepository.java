package com.example.abw.repositories.advertisement;

import com.example.abw.entities.advertisement.CarAdvertisement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarAdvertisementRepository extends CrudRepository<CarAdvertisement, Long> {
    public List<CarAdvertisement> readAllByCarBrand_CarBrandName_Name(String name);
}
