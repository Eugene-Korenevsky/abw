package com.example.abw.kafka.car_ad;

import com.example.abw.model.kafka.KafkaCarAdDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CarAdKafkaConfig {

    private final String bootstrapAddress = "localhost:9092";

    public ConsumerFactory<String, KafkaCarAdDTO> carAdKafkaConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "car_adGroup");
        JsonDeserializer<KafkaCarAdDTO> jsonDeserializer
                = new JsonDeserializer<>(KafkaCarAdDTO.class, false);
        jsonDeserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), jsonDeserializer);
    }

    @Bean
    public KafkaTemplate<String, KafkaCarAdDTO> carAdKafkaTemplate() {
        return new KafkaTemplate<>(carAdDTOProducerFactory());
    }

    @Bean
    public ProducerFactory<String, KafkaCarAdDTO> carAdDTOProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaCarAdDTO> carAdKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, KafkaCarAdDTO> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(carAdKafkaConsumerFactory());
        return factory;
    }
}
