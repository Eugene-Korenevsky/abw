package com.example.abw.model.advertisement.car_advertisement;

import com.example.abw.model.currency.Currency;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data
@EqualsAndHashCode
public class CarAdvertisementDTOAdd {
   // private long userId;
    private long carBrandId;
    private long price;
    private String descriptions;
    private Set<MultipartFile> images;
    private Currency currency;
}
