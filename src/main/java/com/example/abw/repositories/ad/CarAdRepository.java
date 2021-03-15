package com.example.abw.repositories.ad;

import com.example.abw.entities.ad.CarAd;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarAdRepository extends CrudRepository<CarAd, Long> {
    public List<CarAd> readAllByCarBrand_CarBrandName_Name(String name);
}
