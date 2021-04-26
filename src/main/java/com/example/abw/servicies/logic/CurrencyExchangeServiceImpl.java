package com.example.abw.servicies.logic;

import com.example.abw.entities.advertisement.Advertisement;
import com.example.abw.entities.currency.CurrencyEntity;
import com.example.abw.entities.currency.CurrencyExchange;
import com.example.abw.model.currency.Currency;
import com.example.abw.repositories.currency.CurrencyEntityRepository;
import com.example.abw.repositories.currency.CurrencyExchangeRepository;
import com.example.abw.servicies.CurrencyExchangeService;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.Map;

@Service
public class CurrencyExchangeServiceImpl extends GenericServiceImpl<CurrencyEntity>
        implements CurrencyExchangeService {
    private final CurrencyEntityRepository currencyEntityRepository;
    private final CurrencyExchangeRepository currencyExchangeRepository;

    public CurrencyExchangeServiceImpl(CurrencyEntityRepository currencyEntityRepository,
                                       CurrencyExchangeRepository currencyExchangeRepository) {
        super(currencyEntityRepository, CurrencyEntity.class);
        this.currencyEntityRepository = currencyEntityRepository;
        this.currencyExchangeRepository = currencyExchangeRepository;
    }

    @Override
    public long getPrice(Currency main, Currency currencyTo, long price) {
        CurrencyEntity currencyEntityMain = currencyEntityRepository
                .findByCurrency(main);
        CurrencyEntity currencyEntityTo = currencyEntityRepository.findByCurrency(currencyTo);
        CurrencyExchange currencyExchange = currencyExchangeRepository.
                findByCurrencyEntityMainAndCurrencyEntityTo(currencyEntityMain, currencyEntityTo);
        double exchangePrice = currencyExchange.getValue();
        if (exchangePrice >= 1) {
            long exchange = (long) (exchangePrice * 10000);
            long res = price * exchange;
            return res / 100;
        } else {
            long exchange = (long) (exchangePrice * 100000);
            long res = price * exchange;
            return res / 100000;
        }

    }

    @Override
    public void updateCurrencyExchanges(Map jsonResource, Currency currency) {
        CurrencyEntity currencyEntity = currencyEntityRepository.findByCurrency(currency);
        if (currencyEntity != null) {
            EnumSet<Currency> currencies = EnumSet.allOf(Currency.class);

            for (Currency currency1 : currencies) {
                if (!currencyEntity.getCurrency().equals(currency1)) {
                    CurrencyEntity currencyEntityTo = currencyEntityRepository.findByCurrency(currency1);
                    CurrencyExchange currencyExchange =
                            currencyExchangeRepository.
                                    findByCurrencyEntityMainAndCurrencyEntityTo(currencyEntity, currencyEntityTo);
                    if (currencyExchange != null) {
                        currencyExchange.setValue((Double) jsonResource.get(String.valueOf(currency1)));
                        currencyExchangeRepository.save(currencyExchange);
                    } else {
                        CurrencyExchange currencyExchange1 = new CurrencyExchange();
                        currencyExchange1.setCurrencyEntityMain(currencyEntity);
                        currencyExchange1.setCurrencyEntityTo(currencyEntityTo);
                        currencyExchange1.setValue((Double) jsonResource.get(String.valueOf(currency1)));
                        currencyExchangeRepository.save(currencyExchange1);
                    }
                } else {
                    CurrencyExchange currencyExchange =
                            currencyExchangeRepository.
                                    findByCurrencyEntityMainAndCurrencyEntityTo(currencyEntity, currencyEntity);
                    if (currencyExchange != null) {
                        currencyExchange.setValue(1);
                        currencyExchangeRepository.save(currencyExchange);
                    } else {
                        CurrencyExchange currencyExchange1 = new CurrencyExchange();
                        currencyExchange1.setCurrencyEntityMain(currencyEntity);
                        currencyExchange1.setCurrencyEntityTo(currencyEntity);
                        currencyExchange1.setValue(1);
                        currencyExchangeRepository.save(currencyExchange1);
                    }
                }
            }
        }
    }
}
