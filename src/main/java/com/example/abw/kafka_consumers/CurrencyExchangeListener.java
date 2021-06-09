package com.example.abw.kafka_consumers;


import com.example.abw.entities.currency.CurrencyExchange;
import com.example.abw.model.currency.CurrencyExchangeDTO;
import com.example.abw.servicies.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
@EnableKafka
public class CurrencyExchangeListener {
    @Autowired
    private CurrencyExchangeService currencyExchangeService;

    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    @org.springframework.kafka.annotation.KafkaListener(topics = "currency_exchange",
            containerFactory = "exchangeKafkaListenerContainerFactory")
    public void currencyExchangeListening(CurrencyExchangeDTO currencyExchange) {
        currencyExchangeService.updateCurrencyExchange(currencyExchange);
        this.countDownLatch.countDown();
    }
}
