package com.example.abw.model.advertisement.car_advertisement;

import com.example.abw.model.currency.Currency;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Set;

@Data
@EqualsAndHashCode
public class CarAdvertisementDTOAdd {
    private long carBrandId;
    private BigDecimal price;
    private String descriptions;
    private Set<MultipartFile> images;
    private Currency currency;
}
