package com.example.abw.servicies;


import com.example.abw.entities.advertisement.Advertisement;
import com.example.abw.entities.advertisement.CarAdvertisement;
import com.example.abw.exception.security.PrivacyViolationException;
import com.example.abw.model.pageable.PageableParams;
import com.example.abw.model.car_advertisement.CarAdvertisementRequest;
import com.example.abw.exception.entities.ResourceNotFoundException;
import com.example.abw.exception.validation.ValidationException;

import java.io.IOException;
import java.util.List;

public interface CarAdvertisementService extends GenericService<CarAdvertisement> {
    public CarAdvertisement createCarAdvertisement(CarAdvertisementRequest carAdvertisementRequest)
            throws ValidationException, IOException,ResourceNotFoundException,PrivacyViolationException;

    public CarAdvertisement updateCarAdvertisement(CarAdvertisementRequest carAdvertisementRequest, long id)
            throws ValidationException, ResourceNotFoundException, PrivacyViolationException;

    public void softDelete(long id)
            throws ResourceNotFoundException, ValidationException,PrivacyViolationException;

    public List<Advertisement> findAllByCarBrand(String carBrand, boolean isAdmin, PageableParams pageableParams);

    public List<Advertisement> findAllByCarBrandName(String carBrandName, boolean isAdmin, PageableParams pageableParams);

    public List<Advertisement> findAllByPrice(Long startPrice, Long endPrice, boolean isAdmin, PageableParams pageableParams);

    public List<Advertisement> findAllByPriceAndCarBrand(Long startPrice, Long endPrice, String carBrand,
                                                         boolean isAdmin, PageableParams pageableParams);

    public List<Advertisement> findAllByPriceAndCarBrandName(Long startPrice, Long endPrice, String carBrandName,
                                                             boolean isAdmin, PageableParams pageableParams);

    public List<Advertisement> findAll(boolean isAdmin, PageableParams pageableParams);


}
