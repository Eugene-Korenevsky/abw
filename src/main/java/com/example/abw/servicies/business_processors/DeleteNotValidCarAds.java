package com.example.abw.servicies.business_processors;

import com.example.abw.AppProperties;
import com.example.abw.entities.advertisement.CarAdvertisement;
import com.example.abw.model.advertisement.Status;
import com.example.abw.repositories.advertisement.CarAdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Component
@EnableAsync
public class DeleteNotValidCarAds {

    @Autowired
    private AppProperties appProperties;
    @Autowired
    private CarAdvertisementRepository carAdvertisementRepository;

    @Transactional
    @Async
    @Scheduled(fixedRate = 3600000)
    public void deleteNotValidCarAds() {
        Date date = new Date();
        long time = date.getTime() - appProperties.getActiveTime();
        Timestamp timestamp = new Timestamp(time);
        List<CarAdvertisement> carAdvertisements = carAdvertisementRepository
                .readAllByStatusAndPublicationDateLessThan(Status.NOT_VALID, timestamp);
        carAdvertisementRepository.deleteAll(carAdvertisements);
    }
}
