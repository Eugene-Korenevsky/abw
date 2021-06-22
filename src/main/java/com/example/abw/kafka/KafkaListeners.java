package com.example.abw.kafka;

import com.example.abw.exception.entities.ResourceNotFoundException;
import com.example.abw.model.currency.CurrencyExchangeDTO;
import com.example.abw.model.kafka.KafkaCarAdDTO;
import com.example.abw.servicies.CarAdvertisementService;
import com.example.abw.servicies.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
@EnableKafka
@PropertySource("classpath:kafka.properties")
public class KafkaListeners {

    @Autowired
    private CarAdvertisementService carAdvertisementService;

    @Autowired
    private CurrencyExchangeService currencyExchangeService;

    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    @KafkaListener(topics = "car_ad-result", groupId = "car_adGroup",
            containerFactory = "kafkaJsonListenerContainerFactory")
    public void carAdResultListening(KafkaCarAdDTO kafkaCarAdDTO) throws ResourceNotFoundException {
        carAdvertisementService.confirmCarAdvertisement(kafkaCarAdDTO);
    }

    @KafkaListener(topics = "currency_exchange", groupId = "currency_exchangeGroup",
            containerFactory = "kafkaJsonListenerContainerFactory")
    public void currencyExchangeListening(CurrencyExchangeDTO currencyExchange) {
        currencyExchangeService.updateCurrencyExchange(currencyExchange);
        this.countDownLatch.countDown();
    }
}
