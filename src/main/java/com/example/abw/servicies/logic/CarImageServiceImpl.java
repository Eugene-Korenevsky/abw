package com.example.abw.servicies.logic;


import com.example.abw.entities.ad.image.car.CarImage;
import com.example.abw.repositories.ad.image.car.CarImageRepository;
import com.example.abw.servicies.CarImageService;
import org.springframework.stereotype.Service;

@Service
public class CarImageServiceImpl extends GenericServiceImpl<CarImage> implements CarImageService {
    private CarImageRepository carImageRepository;

    public CarImageServiceImpl(CarImageRepository carImageRepository) {
        super(carImageRepository, CarImage.class);
        this.carImageRepository = carImageRepository;
    }


}
