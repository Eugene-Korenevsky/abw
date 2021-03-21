package com.example.abw.servicies.logic;


import com.example.abw.entities.sell_item.car.CarBrand;
import com.example.abw.repositories.sell_item.car.CarBrandRepository;
import com.example.abw.servicies.CarBrandService;
import org.springframework.stereotype.Service;

@Service
public class CarBrandServiceImpl extends GenericServiceImpl<CarBrand> implements CarBrandService {
    private CarBrandRepository carBrandRepository;

    public CarBrandServiceImpl(CarBrandRepository carBrandRepository) {
        super(carBrandRepository, CarBrand.class);
        this.carBrandRepository = carBrandRepository;
    }
}
