package com.example.abw.model.kafka;

import com.example.abw.entities.advertisement.CarAdvertisement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface KafkaCarAdMapper {
    @Mappings({
            @Mapping(source = "user", target = "userDTO"),
            @Mapping(target = "carBrandName", source = "carBrand.carBrandName.name"),
            @Mapping(target = "carBrand", source = "carBrand.name")
    })
    public KafkaCarAdDTO carAdvertisementToKafkaCarAdDTO(CarAdvertisement carAdvertisement);
}
