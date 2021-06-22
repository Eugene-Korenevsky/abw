package com.example.abw.kafka;

import com.example.abw.entities.advertisement.CarAdvertisement;
import com.example.abw.model.kafka.KafkaCarAdDTO;
import com.example.abw.model.kafka.KafkaCarAdMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:kafka.properties")
public class KafkaProducers {
    @Autowired
    private KafkaTemplate<String, KafkaCarAdDTO> kafkaTemplate;
    @Autowired
    private KafkaCarAdMapper kafkaCarAdMapper;
    @Value("${car.ad.topic}")
    private String carAdTopic;

    public void sendCarAd(CarAdvertisement carAdvertisement) {
        KafkaCarAdDTO kafkaCarAdDTO = kafkaCarAdMapper.carAdvertisementToKafkaCarAdDTO(carAdvertisement);
        ProducerRecord<String, KafkaCarAdDTO> record = new ProducerRecord<>(carAdTopic, kafkaCarAdDTO);
        kafkaTemplate.send(record);
    }
}
