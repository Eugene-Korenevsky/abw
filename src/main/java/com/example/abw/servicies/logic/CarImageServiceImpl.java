package com.example.abw.servicies.logic;


import com.example.abw.entities.advertisement.CarAdvertisement;
import com.example.abw.entities.advertisement.image.Image;
import com.example.abw.entities.advertisement.image.car.CarImage;
import com.example.abw.exception.entities.ResourceNotFoundException;
import com.example.abw.exception.security.PrivacyViolationException;
import com.example.abw.exception.validation.ValidationException;
import com.example.abw.repositories.advertisement.image.car.CarImageRepository;
import com.example.abw.security.utils.UserUtil;
import com.example.abw.servicies.CarAdvertisementService;
import com.example.abw.servicies.CarImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class CarImageServiceImpl extends GenericServiceImpl<CarImage> implements CarImageService {
    private final CarImageRepository carImageRepository;
    private final CarAdvertisementService carAdvertisementService;
    private final UserUtil userUtil;

    public CarImageServiceImpl(CarImageRepository carImageRepository, CarAdvertisementService carAdvertisementService,
                               UserUtil userUtil) {
        super(carImageRepository, CarImage.class);
        this.carImageRepository = carImageRepository;
        this.carAdvertisementService = carAdvertisementService;
        this.userUtil = userUtil;
    }

    @Override
    public Set<byte[]> getImagesByCarAdvertisement(long carAdId) throws ResourceNotFoundException {
        CarAdvertisement carAd = carAdvertisementService.findById(carAdId);
        Set<byte[]> images = new HashSet<>();
        for (CarImage carImage : carAd.getCarImages()) {
            images.add(carImage.getContent());
        }
        return images;
    }

    @Transactional
    @Override
    public Image createAdvertisementImage(byte[] content, long carAdId)
            throws PrivacyViolationException, ValidationException,ResourceNotFoundException {
        CarAdvertisement carAdvertisement = carAdvertisementService.findById(carAdId);
        if (userUtil.getCustomUserDetails().getUsername().equals(carAdvertisement.getUser().getEmail())) {
            CarImage image = new CarImage();
            image.setCarAd(carAdvertisement);
            image.setContent(content);
            return create(image);
        } else throw new PrivacyViolationException("privacy violation");
    }

    @Override
    public void deleteCarAdvertisementImage(long imageId) throws ResourceNotFoundException,PrivacyViolationException {
        CarImage carImage = findById(imageId);
        if (carImage.getCarAd().getUser().getEmail().equals(userUtil.getCustomUserDetails().getUsername()))
            carImageRepository.deleteById(imageId);
        else throw new PrivacyViolationException("privacy violation");
    }
}
