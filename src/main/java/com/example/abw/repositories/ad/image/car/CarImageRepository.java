package com.example.abw.repositories.ad.image.car;

import com.example.abw.entities.ad.image.car.CarImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarImageRepository extends CrudRepository<CarImage, Long> {
    public CarImage findByImage(String image);
}
