package com.example.abw.model.car_advertisement;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data
@EqualsAndHashCode
public class CarAdvertisementRequest {
   // private long userId;
    private long carBrandId;
    private long price;
    private String descriptions;
    private Set<MultipartFile> images;
}
