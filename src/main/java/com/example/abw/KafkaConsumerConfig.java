package com.example.abw;

import com.example.abw.entities.currency.CurrencyExchange;
import com.example.abw.model.currency.CurrencyExchangeDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;


import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    String bootstrapAddress = "localhost:9092";

    public ConsumerFactory<String, CurrencyExchangeDTO> currencyExchangeConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "currency_exchange");
        JsonDeserializer<CurrencyExchangeDTO> jsonDeserializer
                = new JsonDeserializer<>(CurrencyExchangeDTO.class, false);
        jsonDeserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CurrencyExchangeDTO> exchangeKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CurrencyExchangeDTO> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(currencyExchangeConsumerFactory());
        return factory;
    }
}
