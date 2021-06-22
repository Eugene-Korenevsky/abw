package com.example.abw.client;

import com.example.abw.AppProperties;
import com.example.abw.exception.client.CryptoCompareClientResponseErrorHandling;
import com.example.abw.model.currency.Currency;
import com.example.abw.utils.CurrencyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class CryptoCompareClient {
    @Autowired
    private AppProperties appProperties;
    @Autowired
    private CurrencyUtil currencyUtil;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CryptoCompareClientResponseErrorHandling responseErrorHandling;

    public Map<String, Double> getCurrencyExchanges(Currency currency) {
        String resourceUrl = appProperties.getCryptoCompareUrl() + (currency) + appProperties.getCryptoCompareTo()
                + currencyUtil.getCurrencyString(currency) + appProperties.getCryptoCompareKey();
        restTemplate.setErrorHandler(responseErrorHandling);
        return restTemplate.getForObject(resourceUrl, Map.class);
    }
}
