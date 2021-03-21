package com.example.abw.servicies.logic;

import com.example.abw.entities.ad.CarAd;
import com.example.abw.entities.request.ad.CarAdRequest;
import com.example.abw.entities.sell_item.car.CarBrand;
import com.example.abw.entities.user.User;
import com.example.abw.repositories.ad.CarAdRepository;
import com.example.abw.servicies.CarAdService;
import com.example.abw.servicies.CarBrandService;
import com.example.abw.servicies.UserService;
import com.example.abw.servicies.exceptions.ResourceNotFoundException;
import com.example.abw.validator.exception.ValidationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.Date;

@Service
public class CarAdServiceImpl extends GenericServiceImpl<CarAd> implements CarAdService {
    private CarAdRepository carAdRepository;
    private CarBrandService carBrandServiceImpl;
    private UserService userServiceImpl;

    public CarAdServiceImpl(CarAdRepository carAdRepository, UserService userServiceImpl,
                            CarBrandService carBrandServiceImpl) {
        super(carAdRepository, CarAd.class);
        this.carAdRepository = carAdRepository;
        this.userServiceImpl = userServiceImpl;
        this.carBrandServiceImpl = carBrandServiceImpl;
    }

    @Override
    public CarAd createCarAd(CarAdRequest carAdRequest) throws ValidationException {
        CarAd carAd = new CarAd();
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        User user = isCorrectUser(carAdRequest.getUserId());
        CarBrand carBrand = findCarBrand(carAdRequest.getCarBrandId());
        carAd.setPublicationDate(timestamp);
        carAd.setCarBrand(carBrand);
        carAd.setDescriptions(carAdRequest.getDescriptions());
        carAd.setPrice(carAdRequest.getPrice());
        carAd.setUser(user);
        return create(carAd);
    }

    @Override
    public CarAd updateCarAd(CarAdRequest carAdRequest, long id) throws ValidationException, ResourceNotFoundException {
        CarAd carAd = findById(id);
        User user = isCorrectUser(carAdRequest.getUserId());
        CarBrand carBrand = findCarBrand(carAdRequest.getCarBrandId());
        carAd.setCarBrand(carBrand);
        carAd.setDescriptions(carAdRequest.getDescriptions());
        carAd.setPrice(carAdRequest.getPrice());
        carAd.setUser(user);
        return update(carAd, id);
    }

    @Override
    public void softDelete(long id) throws ResourceNotFoundException {
        CarAd carAd = findById(id);
        carAd.setSold(true);
        carAdRepository.save(carAd);
    }

    private User isCorrectUser(long id) throws ValidationException {
        User user;
        try {
            user = userServiceImpl.findById(id);
        } catch (ResourceNotFoundException e) {
            throw new ValidationException("user not found");
        }
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        org.springframework.security.core.userdetails.User userContext =
                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        if (!user.getEmail().equals(userContext.getUsername()))
            throw new ValidationException("not Correct user");
        return user;

    }

    private CarBrand findCarBrand(long id) throws ValidationException {
        try {
            return carBrandServiceImpl.findById(id);
        } catch (ResourceNotFoundException e) {
            throw new ValidationException("carBrand not found");
        }
    }
}
