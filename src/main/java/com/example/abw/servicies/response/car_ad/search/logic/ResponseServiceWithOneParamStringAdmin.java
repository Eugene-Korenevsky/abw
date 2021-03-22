package com.example.abw.servicies.response.car_ad.search.logic;

import com.example.abw.servicies.response.car_ad.search.ResponseServiceWithOneParam;
import org.springframework.stereotype.Service;

@Service
public class ResponseServiceWithOneParamStringAdmin extends ResponseServiceWithOneParam<String> {
    @Override
    public boolean isAdmin() {
        return true;
    }
}
