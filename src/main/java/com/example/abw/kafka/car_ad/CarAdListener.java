package com.example.abw.kafka.car_ad;

import com.example.abw.exception.entities.ResourceNotFoundException;
import com.example.abw.model.kafka.KafkaCarAdDTO;
import com.example.abw.servicies.CarAdvertisementService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@EnableKafka
public class CarAdListener {

    @Autowired
    private CarAdvertisementService carAdvertisementService;

    @KafkaListener(topics = "car_ad-result", groupId = "car_adGroup", containerFactory = "carAdKafkaListenerContainerFactory")
    public void listenCarAd(ConsumerRecord<String, KafkaCarAdDTO> record) throws ResourceNotFoundException {
        carAdvertisementService.confirmCarAdvertisement(record.value());
    }
}
