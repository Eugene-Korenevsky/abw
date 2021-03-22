package com.example.abw.servicies.response.car_ad.search.logic;

import com.example.abw.servicies.response.car_ad.search.ResponseServiceWithThreeParam;
import com.example.abw.servicies.response.car_ad.search.ResponseServiceWithTwoParam;
import org.springframework.stereotype.Service;

@Service
public class ResponseServiceByPriceAndStringAdmin extends ResponseServiceWithThreeParam<Long, Long, String> {
    @Override
    public boolean isAdmin() {
        return true;
    }
}
