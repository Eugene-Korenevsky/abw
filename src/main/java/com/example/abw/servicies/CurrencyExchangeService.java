package com.example.abw.servicies;

import com.example.abw.entities.currency.CurrencyExchange;
import com.example.abw.model.currency.Currency;

import java.math.BigDecimal;
import java.util.Map;

public interface CurrencyExchangeService extends GenericService<CurrencyExchange> {
    public void updateCurrencyExchanges(Map<String, Double> jsonResource, Currency currency);

    public BigDecimal getPrice(Currency main , Currency currencyTo, BigDecimal price);
}
