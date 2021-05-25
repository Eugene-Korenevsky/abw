package com.example.abw.servicies;


import com.example.abw.entities.advertisement.CarAdvertisement;
import com.example.abw.exception.security.PrivacyViolationException;
import com.example.abw.model.advertisement.car_advertisement.CarAdvertisementResponse;
import com.example.abw.model.currency.Currency;
import com.example.abw.model.pageable.PageableParams;
import com.example.abw.model.advertisement.car_advertisement.CarAdvertisementDTOAdd;
import com.example.abw.exception.entities.ResourceNotFoundException;
import com.example.abw.exception.validation.ValidationException;

import java.io.IOException;
import java.util.List;

public interface CarAdvertisementService extends GenericService<CarAdvertisement> {
    public CarAdvertisementResponse createCarAdvertisement(CarAdvertisementDTOAdd carAdvertisementDTOAdd)
            throws ValidationException, IOException, ResourceNotFoundException, PrivacyViolationException;

    public CarAdvertisementResponse updateCarAdvertisement(CarAdvertisementDTOAdd carAdvertisementDTOAdd, long id)
            throws ValidationException, ResourceNotFoundException, PrivacyViolationException;

    public CarAdvertisementResponse findAdvertisement(long advertisementId, Currency currency)
            throws ResourceNotFoundException;

    public CarAdvertisementResponse findUserAdvertisement(long advertisementId)
            throws ResourceNotFoundException, PrivacyViolationException;

    public CarAdvertisementResponse refreshCarAdvertisement(long advertisementId)
            throws ResourceNotFoundException, PrivacyViolationException;

    public List<CarAdvertisementResponse> findAllByUser(
            PageableParams pageableParams) throws ResourceNotFoundException,ValidationException;

    public void softDelete(long id)
            throws ResourceNotFoundException, ValidationException, PrivacyViolationException;

    public List<CarAdvertisementResponse> findAllByCarBrand(
            String carBrand, boolean isAdmin, PageableParams pageableParams) throws ValidationException;

    public List<CarAdvertisementResponse> findAllByCarBrandName(
            String carBrandName, boolean isAdmin, PageableParams pageableParams)throws ValidationException;

    public List<CarAdvertisementResponse> findAllByPrice(
            Long startPrice, Long endPrice, boolean isAdmin, PageableParams pageableParams) throws ValidationException;

    public List<CarAdvertisementResponse> findAllByPriceAndCarBrand(Long startPrice, Long endPrice, String carBrand,
                                                         boolean isAdmin, PageableParams pageableParams) throws ValidationException;

    public List<CarAdvertisementResponse> findAllByPriceAndCarBrandName(Long startPrice, Long endPrice, String carBrandName,
                                                             boolean isAdmin, PageableParams pageableParams) throws ValidationException;

    public List<CarAdvertisementResponse> findAll(boolean isAdmin, PageableParams pageableParams) throws ValidationException;


}
