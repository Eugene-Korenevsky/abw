package com.example.abw.utils.message;

import com.example.abw.AppProperties;
import com.example.abw.entities.advertisement.CarAdvertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageTextUtil {
    private static AppProperties appProperties;

    public static String getCarAdvertisementExpiredMessageText(CarAdvertisement carAdvertisement) {
        return "Dear " + carAdvertisement.getUser().getName() + " " + appProperties.getAdvertisementText() + " " +
                carAdvertisement.getCarBrand().getFullName() + " " + appProperties.getCarAdvertisementMessage();
    }

    public static String getCarAdNotValidMessageText(CarAdvertisement carAdvertisement, String reason) {
        return "Dear " + carAdvertisement.getUser().getName() + " " + appProperties.getAdvertisementText() + " " +
                carAdvertisement.getCarBrand().getFullName() + " " + appProperties.getNotValidAd() + " because of " +
                reason;
    }

    public static String getCarAdValidMessageText(CarAdvertisement carAdvertisement) {
        return "Dear " + carAdvertisement.getUser().getName() + " " + appProperties.getAdvertisementText() + " " +
                " " + carAdvertisement.getCarBrand().getFullName() + " " + appProperties.getValidAd();
    }

    public static String getCarAdOnCheckMessageText(CarAdvertisement carAdvertisement) {
        return "Dear " + carAdvertisement.getUser().getName() + " " + appProperties.getAdvertisementText() + " " +
                carAdvertisement.getCarBrand().getFullName() + " " + appProperties.getOnCheckAd();
    }

    @Autowired
    public void setAppProperties(AppProperties appProperties) {
        MessageTextUtil.appProperties = appProperties;
    }
}
