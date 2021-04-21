package com.example.abw.model.car_ad;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode
public class CarAdRequest {
    private long userId;
    private long carBrandId;
    private long price;
    private String descriptions;
    private Set<String> images;
}
