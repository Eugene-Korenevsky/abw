package com.example.abw.servicies.logic;


import com.example.abw.entities.advertisement.CarAdvertisement;
import com.example.abw.entities.advertisement.image.Image;
import com.example.abw.entities.advertisement.image.car.CarImage;
import com.example.abw.exception.entities.ResourceNotFoundException;
import com.example.abw.exception.security.PrivacyViolationException;
import com.example.abw.exception.validation.ValidationException;
import com.example.abw.repositories.advertisement.CarAdvertisementRepository;
import com.example.abw.repositories.advertisement.CarImageRepository;
import com.example.abw.security.utils.UserUtil;
import com.example.abw.servicies.CarImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class CarImageServiceImpl extends GenericServiceImpl<CarImage> implements CarImageService {
    private final CarAdvertisementRepository carAdvertisementRepository;
    private final CarImageRepository carImageRepository;
    private final UserUtil userUtil;

    public CarImageServiceImpl(CarImageRepository carImageRepository, UserUtil userUtil, CarAdvertisementRepository carAdvertisementRepository) {
        super(carImageRepository, CarImage.class);
        this.carImageRepository = carImageRepository;
        this.userUtil = userUtil;
        this.carAdvertisementRepository = carAdvertisementRepository;
    }

    @Override
    public Set<byte[]> getImagesByCarAdvertisement(long carAdId) throws ResourceNotFoundException {
        CarAdvertisement carAd = carAdvertisementRepository.findById(carAdId)
                .orElseThrow(() -> new ResourceNotFoundException("resource not found"));
        Set<byte[]> images = new HashSet<>();
        for (CarImage carImage : carAd.getCarImages()) {
            images.add(carImage.getContentImage());
        }
        return images;
    }

    @Transactional
    @Override
    public Image createAdvertisementImage(byte[] content, long carAdId)
            throws PrivacyViolationException, ValidationException, ResourceNotFoundException {
        CarAdvertisement carAdvertisement = carAdvertisementRepository.findById(carAdId)
                .orElseThrow(() -> new ResourceNotFoundException(("resource not found")));
        if (userUtil.getCustomUserDetails().getUsername().equals(carAdvertisement.getUser().getEmail())) {
            CarImage image = new CarImage();
            image.setCarAd(carAdvertisement);
            image.setContentImage(content);
            return create(image);
        } else throw new PrivacyViolationException("privacy violation");
    }

    @Override
    public void deleteCarAdvertisementImage(long imageId) throws ResourceNotFoundException, PrivacyViolationException {
        CarImage carImage = findById(imageId);
        if (carImage.getCarAd().getUser().getEmail().equals(userUtil.getCustomUserDetails().getUsername()))
            carImageRepository.deleteById(imageId);
        else throw new PrivacyViolationException("privacy violation");
    }

    @Transactional
    @Override
    public void createCarAdImages(Set<MultipartFile> images, long carAdId) throws ResourceNotFoundException, PrivacyViolationException, IOException {
        CarAdvertisement carAdvertisement = carAdvertisementRepository.findById(carAdId)
                .orElseThrow(() -> new ResourceNotFoundException(("resource not found")));
        if (userUtil.getCustomUserDetails().getUsername().equals(carAdvertisement.getUser().getEmail())) {
            Set<CarImage> carImages = new HashSet<>();
            for (MultipartFile multipartFile : images) {
                if (multipartFile != null) {
                    CarImage carImage = new CarImage();
                    carImage.setCarAd(carAdvertisement);
                    carImage.setContentImage(multipartFile.getBytes());
                    carImages.add(carImage);
                }
            }
            saveAll(new ArrayList<>(carImages));
        } else throw new PrivacyViolationException("privacy violation");
    }
}
