package com.example.abw.exception.client;

import com.example.abw.repositories.currency.CurrencyExRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Component
public class CryptoCompareClientResponseErrorHandling implements ResponseErrorHandler {
    @Autowired
    private CurrencyExRepository currencyExRepository;

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return (clientHttpResponse.getStatusCode().series() == CLIENT_ERROR
                || clientHttpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        System.out.println(clientHttpResponse.getStatusCode());
        /** here we can add some action when we have response error **/
    }
}
