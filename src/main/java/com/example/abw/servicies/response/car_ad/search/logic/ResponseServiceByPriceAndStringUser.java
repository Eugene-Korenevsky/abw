package com.example.abw.servicies.response.car_ad.search.logic;

import com.example.abw.servicies.response.car_ad.search.ResponseServiceWithThreeParam;
import org.springframework.stereotype.Service;

@Service
public class ResponseServiceByPriceAndStringUser extends ResponseServiceWithThreeParam<Long, Long, String> {
    @Override
    public boolean isAdmin() {
        return false;
    }
}
