package com.example.abw.servicies.logic;

import com.example.abw.entities.currency.CurrencyExchange;
import com.example.abw.model.currency.Currency;
import com.example.abw.repositories.currency.CurrencyExRepository;
import com.example.abw.servicies.CurrencyExchangeService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.Map;

@Service
public class CurrencyExchangeServiceImpl extends GenericServiceImpl<CurrencyExchange>
        implements CurrencyExchangeService {
    private final CurrencyExRepository currencyExRepository;

    public CurrencyExchangeServiceImpl(CurrencyExRepository currencyExRepository) {
        super(currencyExRepository, CurrencyExchange.class);
        this.currencyExRepository = currencyExRepository;
    }

    @Override
    public BigDecimal getPrice(Currency main, Currency currencyTo, BigDecimal price) {
        if (main.equals(currencyTo)) return price;
        CurrencyExchange currencyExchange = currencyExRepository.findByCurrencyMainAndCurrencyTo(main, currencyTo);
        return currencyExchange.getValue().multiply(price);
    }

    @Override
    public void updateCurrencyExchanges(Map<String, Double> jsonResource, Currency currency) {
        EnumSet<Currency> currencies = EnumSet.allOf(Currency.class);
        for (Currency currentCurrency : currencies) {
            if (!currency.equals(currentCurrency)) {
                CurrencyExchange currencyExchange = currencyExRepository.
                        findByCurrencyMainAndCurrencyTo(currency, currentCurrency);
                if (currencyExchange == null) {
                    currencyExchange = new CurrencyExchange();
                    currencyExchange.setCurrencyMain(currency);
                    currencyExchange.setCurrencyTo(currentCurrency);
                }
                currencyExchange.setValue(BigDecimal.valueOf(jsonResource.get(String.valueOf(currentCurrency))));
                currencyExRepository.save(currencyExchange);
            }
        }
    }
}
