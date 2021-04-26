package com.example.abw.servicies.business_processors;

import com.example.abw.AppProperties;
import com.example.abw.model.currency.Currency;
import com.example.abw.servicies.CurrencyEntityService;
import com.example.abw.servicies.CurrencyExchangeService;
import com.example.abw.utils.currency.CurrencyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import java.util.EnumSet;
import java.util.Map;

@Component
public class CurrencyExchangeUpdater {
    @Autowired
    private AppProperties appProperties;
    @Autowired
    private CurrencyUtil currencyUtil;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CurrencyExchangeService currencyExchangeService;
    @Autowired
    private CurrencyEntityService currencyEntityService;

    @Scheduled(fixedRate = 86400000)
    public void updateCurrencyExchange() {
        EnumSet<Currency> currencies = EnumSet.allOf(Currency.class);
        currencyEntityService.updateAppCurrencies();
        for (Currency currency : currencies) {
            String resourceUrl = appProperties.getCryptoCompareUrl() + (currency) + appProperties.getCryptoCompareTo()
                    + currencyUtil.getCurrencyString() + appProperties.getCryptoCompareKey();
            Map jsonResource = restTemplate.getForObject(resourceUrl, Map.class);
            currencyExchangeService.updateCurrencyExchanges(jsonResource, currency);
        }
    }
}
