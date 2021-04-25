package com.example.abw.servicies;

import com.example.abw.entities.currency.CurrencyEntity;

public interface CurrencyEntityService extends GenericService<CurrencyEntity> {
    public void updateAppCurrencies();
}
