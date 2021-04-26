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
    public long getPrice(Currency main, Currency currencyTo, long price) {
        CurrencyExchange currencyExchange = currencyExRepository.findByCurrencyMainAndCurrencyTo(main, currencyTo);
        double exchangePrice = currencyExchange.getValue().doubleValue();
        long exchange = (long) (exchangePrice * 10000);
        long res = price * exchange;
        return res / 10000;
    }

    @Override
    public void updateCurrencyExchanges(Map<String, Double> jsonResource, Currency currency) {
        EnumSet<Currency> currencies = EnumSet.allOf(Currency.class);
        for (Currency currency1 : currencies) {
            if (currency.equals(currency1)) {
                CurrencyExchange currencyExchange = currencyExRepository.findByCurrencyMainAndCurrencyTo(currency, currency1);
                if (currencyExchange == null) {
                    currencyExchange = new CurrencyExchange();
                    currencyExchange.setCurrencyMain(currency);
                    currencyExchange.setCurrencyTo(currency1);
                }
                currencyExchange.setValue(BigDecimal.valueOf(1));
                currencyExRepository.save(currencyExchange);
            } else {
                CurrencyExchange currencyExchange = currencyExRepository.findByCurrencyMainAndCurrencyTo(currency, currency1);
                if (currencyExchange == null) {
                    currencyExchange = new CurrencyExchange();
                    currencyExchange.setCurrencyMain(currency);
                    currencyExchange.setCurrencyTo(currency1);
                }
                currencyExchange.setValue(BigDecimal.valueOf(jsonResource.get(String.valueOf(currency1))));
                currencyExRepository.save(currencyExchange);
            }

        }
    }
}
