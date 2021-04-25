package com.example.abw.model.advertisement.car_advertisement;

import com.example.abw.entities.advertisement.CarAdvertisement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface CarAdvertisementMapper {

    @Mapping(source = "user", target = "userDTO")
    public CarAdvertisementResponse carAdvertisementToCarAdvertisementResponse(CarAdvertisement carAdvertisement);

    public CarAdvertisement carAdvertisementResponseToCarAdvertisement(
            CarAdvertisementResponse carAdvertisementResponse);

    public List<CarAdvertisementResponse> toCarAdvertisementResponse(List<CarAdvertisement> carAdvertisements);
}
