package com.example.abw.kafka.car_ad;

import com.example.abw.entities.advertisement.CarAdvertisement;
import com.example.abw.model.kafka.KafkaCarAdDTO;
import com.example.abw.model.kafka.KafkaCarAdMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CarAdProducer {
    @Autowired
    private KafkaTemplate<String, KafkaCarAdDTO> carAdKafkaTemplate;
    @Autowired
    private KafkaCarAdMapper kafkaCarAdMapper;

    public void sendCarAd(CarAdvertisement carAdvertisement ) {
        KafkaCarAdDTO kafkaCarAdDTO = kafkaCarAdMapper.carAdvertisementToKafkaCarAdDTO(carAdvertisement);
        ProducerRecord<String, KafkaCarAdDTO> record = new ProducerRecord<>("car_ad",kafkaCarAdDTO);
        carAdKafkaTemplate.send(record);
    }
}
