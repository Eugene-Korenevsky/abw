package com.example.abw.repositories.advertisement.image.car;

import com.example.abw.entities.advertisement.image.car.CarImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarImageRepository extends CrudRepository<CarImage, Long> {

}
