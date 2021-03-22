package com.example.abw.servicies.response.car_ad.search.logic.two_params;

import com.example.abw.servicies.response.car_ad.search.ResponseServiceWithTwoParam;
import org.springframework.stereotype.Service;

@Service
public class ResponseServiceByPriceUser extends ResponseServiceWithTwoParam<Long, Long> {
    @Override
    public boolean isAdmin() {
        return false;
    }
}
