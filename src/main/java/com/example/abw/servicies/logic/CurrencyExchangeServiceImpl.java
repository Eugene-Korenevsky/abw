package com.example.abw.servicies.logic;

import com.example.abw.entities.currency.CurrencyExchange;
import com.example.abw.model.currency.Currency;
import com.example.abw.model.currency.CurrencyExchangeDTO;
import com.example.abw.repositories.currency.CurrencyExchangeRepository;
import com.example.abw.servicies.CurrencyExchangeService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.Map;

@Service
public class CurrencyExchangeServiceImpl extends GenericServiceImpl<CurrencyExchange>
        implements CurrencyExchangeService {
    private final CurrencyExchangeRepository currencyExchangeRepository;

    public CurrencyExchangeServiceImpl(CurrencyExchangeRepository currencyExchangeRepository) {
        super(currencyExchangeRepository, CurrencyExchange.class);
        this.currencyExchangeRepository = currencyExchangeRepository;
    }

    @Override
    public BigDecimal getPrice(Currency main, Currency currencyTo, BigDecimal price) {
        if (main.equals(currencyTo)) return price;
        CurrencyExchange currencyExchange = currencyExchangeRepository.findByCurrencyMainAndCurrencyTo(main, currencyTo);
        return currencyExchange.getValue().multiply(price);
    }

    @Override
    public void updateCurrencyExchange(CurrencyExchangeDTO currencyExchangeDTO) {
        if (currencyExchangeDTO.getValue().doubleValue() > 0) {
            CurrencyExchange currencyExchange = currencyExchangeRepository
                    .findByCurrencyMainAndCurrencyTo(currencyExchangeDTO.getCurrencyMain(),
                            currencyExchangeDTO.getCurrencyTo());
            currencyExchange.setValue(currencyExchangeDTO.getValue());
            currencyExchangeRepository.save(currencyExchange);
        }
    }

    @Override
    public void updateCurrencyExchanges(Map<String, Double> jsonResource, Currency currency) {
        EnumSet<Currency> currencies = EnumSet.allOf(Currency.class);
        for (Currency currentCurrency : currencies) {
            if (!currency.equals(currentCurrency)) {
                CurrencyExchange currencyExchange = currencyExchangeRepository.
                        findByCurrencyMainAndCurrencyTo(currency, currentCurrency);
                if (currencyExchange == null) {
                    currencyExchange = new CurrencyExchange();
                    currencyExchange.setCurrencyMain(currency);
                    currencyExchange.setCurrencyTo(currentCurrency);
                }
                currencyExchange.setValue(BigDecimal.valueOf(jsonResource.get(String.valueOf(currentCurrency))));
                currencyExchangeRepository.save(currencyExchange);
            }
        }
    }
}
