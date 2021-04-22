package com.example.abw.utils.message;

import com.example.abw.AppProperties;
import com.example.abw.entities.advertisement.CarAdvertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageTextUtil {
    @Autowired
    private AppProperties appProperties;

    public String getCarAdvertisementMessageText(CarAdvertisement carAdvertisement) {
        return "Dear " + carAdvertisement.getUser().getName() + " " + appProperties.getAdvertisementText() + " " +
                carAdvertisement.getCarBrand().getFullName() + " " + appProperties.getCarAdvertisementMessage();
    }
}
