package com.example.abw.entities.request.ad;

import com.example.abw.entities.sell_item.SellItem;
import com.example.abw.entities.sell_item.car.CarBrand;
import com.example.abw.entities.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class CarAdRequest  {
    private long userId;
    private long carBrandId;
    private long price;
    private String descriptions;
}
