package com.example.abw.servicies.logic;

import com.example.abw.entities.ad.CarAd;
import com.example.abw.entities.ad.image.Image;
import com.example.abw.entities.ad.image.car.CarImage;
import com.example.abw.entities.request.ad.CarAdRequest;
import com.example.abw.entities.sell_item.car.CarBrand;
import com.example.abw.entities.user.User;
import com.example.abw.repositories.ad.CarAdRepository;
import com.example.abw.repositories.ad.image.car.CarImageRepository;
import com.example.abw.servicies.CarAdService;
import com.example.abw.servicies.CarBrandService;
import com.example.abw.servicies.CarImageService;
import com.example.abw.servicies.UserService;
import com.example.abw.servicies.exceptions.ResourceNotFoundException;
import com.example.abw.validator.exception.ValidationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class CarAdServiceImpl extends GenericServiceImpl<CarAd> implements CarAdService {
    private CarAdRepository carAdRepository;
    private CarBrandService carBrandServiceImpl;
    private UserService userServiceImpl;
    private CarImageRepository carImageRepository;
    private CarImageService carImageService;

    public CarAdServiceImpl(CarAdRepository carAdRepository, UserService userServiceImpl,
                            CarBrandService carBrandServiceImpl, CarImageService carImageService,
                            CarImageRepository carImageRepository) {
        super(carAdRepository, CarAd.class);
        this.carAdRepository = carAdRepository;
        this.userServiceImpl = userServiceImpl;
        this.carBrandServiceImpl = carBrandServiceImpl;
        this.carImageRepository = carImageRepository;
        this.carImageService = carImageService;
    }

    @Override
    public CarAd createCarAd(CarAdRequest carAdRequest) throws ValidationException {
        Set<CarImage> carImages = new HashSet<>();
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
        carAd = create(carAd);
        for (String image : carAdRequest.getImages()) {
            CarImage carImage = new CarImage();
            carImage.setCarAd(carAd);
            carImage.setImage(image);
            carImage = carImageService.create(carImage);
            carImages.add(carImage);
        }
        carAd.setCarImages(carImages);
        return create(carAd);
    }

    @Transactional
    @Override
    public CarAd updateCarAd(CarAdRequest carAdRequest, long id) throws ValidationException, ResourceNotFoundException {
        CarAd carAd = findById(id);
        System.out.println(carAd);
        User user = isCorrectUser(carAdRequest.getUserId());
        CarBrand carBrand = findCarBrand(carAdRequest.getCarBrandId());
        carAd.setCarBrand(carBrand);
        carAd.setDescriptions(carAdRequest.getDescriptions());
        carAd.setPrice(carAdRequest.getPrice());
        carAd.setUser(user);
        update(carAd, id);
        updateImages(carAdRequest, id);
        return findById(id);
    }


    private void updateImages(CarAdRequest carAdRequest, long id) throws ResourceNotFoundException {
        Set<CarImage> carImagesCreate = new HashSet<>();
        Set<CarImage> carImages = new HashSet<>();
        CarAd carAd = findById(id);
        for (String image : carAdRequest.getImages()) {
            CarImage carImage = carImageRepository.findByImage(image);
            if (carImage == null) {
                CarImage carImage1 = new CarImage();
                carImage1.setCarAd(carAd);
                carImage1.setImage(image);
                carImage1 = carImageRepository.save(carImage1);
                carImagesCreate.add(carImage1);
            } else {
                carImagesCreate.add(carImage);
            }
        }
        carImageRepository.saveAll(carImagesCreate);
        for (Image image : carAd.getImages()) {
            if (!carAdRequest.getImages().contains(image.getImage())) {
                carImages.add((CarImage) image);
            }
        }
        for (Image image : carImages) {
            carAd.getCarImages().remove(image);
        }
        carImageRepository.deleteAll(carImages);
    }

    @Transactional(rollbackFor = Exception.class)
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
