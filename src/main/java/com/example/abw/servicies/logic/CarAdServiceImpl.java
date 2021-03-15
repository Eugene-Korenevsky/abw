package com.example.abw.servicies.logic;

import com.example.abw.entities.ad.CarAd;
import com.example.abw.repositories.ad.CarAdRepository;
import com.example.abw.servicies.CarAdService;
import org.springframework.stereotype.Service;

@Service
public class CarAdServiceImpl extends GenericServiceImpl<CarAd> implements CarAdService {
    private CarAdRepository carAdRepository;

    public CarAdServiceImpl(CarAdRepository carAdRepository) {
        super(carAdRepository, CarAd.class);
        this.carAdRepository = carAdRepository;
    }
}
