package com.example.abw.servicies;

import com.example.abw.entities.advertisement.image.Image;
import com.example.abw.entities.advertisement.image.car.CarImage;
import com.example.abw.exception.entities.ResourceNotFoundException;
import com.example.abw.exception.security.PrivacyViolationException;
import com.example.abw.exception.validation.ValidationException;

import java.io.IOException;
import java.util.Set;

public interface CarImageService extends GenericService<CarImage> {

    public Set<byte[]> getImagesByCarAdvertisement(long carAdId) throws ResourceNotFoundException;

    public Image createAdvertisementImage(byte[] content, long carAdId)
            throws PrivacyViolationException, ValidationException,ResourceNotFoundException;

    public void deleteCarAdvertisementImage(long imageId) throws ResourceNotFoundException,PrivacyViolationException;

}
