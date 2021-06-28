package com.example.abw.servicies.business_processors;

import com.example.abw.entities.advertisement.CarAdvertisement;
import com.example.abw.kafka.KafkaProducers;
import com.example.abw.model.advertisement.Status;
import com.example.abw.model.kafka.KafkaCarAdDTO;
import com.example.abw.model.kafka.KafkaCarAdMapper;
import com.example.abw.repositories.advertisement.CarAdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableAsync
@PropertySource("classpath:kafka.properties")
public class ErrorCarAdvertisement {

    @Value("${car.ad.topic}")
    private String carAdTopic;
    @Autowired
    private CarAdvertisementRepository carAdvertisementRepository;
    @Autowired
    private KafkaCarAdMapper kafkaCarAdMapper;
    @Autowired
    private KafkaProducers producers;

    @Scheduled(fixedRate = 600000)
    public void sendErrorsCarAds() {
        List<CarAdvertisement> carAdvertisements = carAdvertisementRepository.readAllByStatus(Status.ON_ERROR);
        for (CarAdvertisement carAdvertisement : carAdvertisements) {
            KafkaCarAdDTO kafkaCarAdDTO = kafkaCarAdMapper.carAdvertisementToKafkaCarAdDTO(carAdvertisement);
            producers.send(kafkaCarAdDTO, carAdTopic);
        }
    }

}
