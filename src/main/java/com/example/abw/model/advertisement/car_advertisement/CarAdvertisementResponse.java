package com.example.abw.model.advertisement.car_advertisement;

import com.example.abw.entities.advertisement.image.car.CarImage;
import com.example.abw.entities.sell_item.car.CarBrand;
import com.example.abw.model.advertisement.Status;
import com.example.abw.model.currency.Currency;
import com.example.abw.model.user.UserDTO;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;

@Data
public class CarAdvertisementResponse {
    private long id;
    private CarBrand carBrand;
    private UserDTO userDTO;
    private long price;
    private Currency priceCurrency;
    private String description;
    private Timestamp publicationDate;
    private Timestamp endPublicationDate;
    private Set<CarImage> carImages;
    private Status status;
}
