package com.example.abw.servicies;


import com.example.abw.entities.ad.Ad;
import com.example.abw.entities.ad.CarAd;
import com.example.abw.model.pageable.PageableParams;
import com.example.abw.entities.request.ad.CarAdRequest;
import com.example.abw.exception.entities.ResourceNotFoundException;
import com.example.abw.exception.validation.ValidationException;

import java.util.List;

public interface CarAdService extends GenericService<CarAd> {
    public CarAd createCarAd(CarAdRequest carAdRequest) throws ValidationException;

    public CarAd updateCarAd(CarAdRequest carAdRequest, long id) throws ValidationException, ResourceNotFoundException;

    public void softDelete(long id) throws ResourceNotFoundException, ValidationException;

    public List<Ad> findAllByCarBrand(String carBrand, boolean isAdmin, PageableParams pageableParams);

    public List<Ad> findAllByCarBrandName(String carBrandName, boolean isAdmin, PageableParams pageableParams);

    public List<Ad> findAllByPrice(Long startPrice, Long endPrice, boolean isAdmin, PageableParams pageableParams);

    public List<Ad> findAllByPriceAndCarBrand(Long startPrice, Long endPrice, String carBrand,
                                              boolean isAdmin, PageableParams pageableParams);

    public List<Ad> findAllByPriceAndCarBrandName(Long startPrice, Long endPrice, String carBrandName,
                                                  boolean isAdmin, PageableParams pageableParams);

    public List<Ad> findAll(boolean isAdmin, PageableParams pageableParams);


}
