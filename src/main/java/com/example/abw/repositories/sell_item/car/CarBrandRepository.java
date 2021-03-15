package com.example.abw.repositories.sell_item.car;

import com.example.abw.entities.sell_item.car.CarBrand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarBrandRepository extends CrudRepository<CarBrand, Long> {
}
