package com.example.abw.repositories.sell_item.car;

import com.example.abw.entities.sell_item.car.CarBrandName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarBrandNameRepository extends CrudRepository<CarBrandName, Long> {
}
