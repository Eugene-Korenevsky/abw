package com.example.abw.servicies;


import com.example.abw.entities.ad.CarAd;
import com.example.abw.entities.request.ad.CarAdRequest;
import com.example.abw.servicies.exceptions.ResourceNotFoundException;
import com.example.abw.validator.exception.ValidationException;

public interface CarAdService extends GenericService<CarAd>{
    public CarAd createCarAd(CarAdRequest carAdRequest) throws ValidationException;

    public CarAd updateCarAd(CarAdRequest carAdRequest,long id) throws ValidationException, ResourceNotFoundException;

    public void softDelete(long id) throws ResourceNotFoundException;
}
