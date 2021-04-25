package com.example.abw.servicies;

import com.example.abw.entities.advertisement.Advertisement;
import com.example.abw.entities.advertisement.CarAdvertisement;
import com.example.abw.entities.currency.CurrencyEntity;
import com.example.abw.model.currency.Currency;

import java.util.Map;

public interface CurrencyExchangeService extends GenericService<CurrencyEntity> {
    public void updateCurrencyExchanges(Map jsonResource, Currency currency);

    public long getPrice(Currency main , Currency currencyTo, long price);
}
