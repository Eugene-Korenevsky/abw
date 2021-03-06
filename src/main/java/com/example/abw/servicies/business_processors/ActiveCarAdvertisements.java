package com.example.abw.servicies.business_processors;

import com.example.abw.AppProperties;
import com.example.abw.entities.advertisement.CarAdvertisement;
import com.example.abw.model.advertisement.Status;
import com.example.abw.repositories.advertisement.CarAdvertisementRepository;
import com.example.abw.servicies.CarAdvertisementService;
import com.example.abw.servicies.MessageService;
import com.example.abw.utils.MessageTextUtil;
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
public class ActiveCarAdvertisements {
    @Autowired
    private CarAdvertisementService carAdvertisementService;
    @Autowired
    private AppProperties appProperties;
    @Autowired
    private CarAdvertisementRepository carAdvertisementRepository;
    @Autowired
    private MessageService messageService;


    @Transactional
    @Async
    @Scheduled(fixedRate = 3600000)
    public void checkActiveCarAdvertisements() {
        Date date = new Date();
        long time = date.getTime() - appProperties.getActiveTime();
        Timestamp timestamp = new Timestamp(time);
        List<CarAdvertisement> carAdvertisements = carAdvertisementRepository
                .readAllByStatusAndPublicationDateLessThan(Status.ACTIVE, timestamp);
        for (CarAdvertisement carAdvertisement : carAdvertisements) {
            carAdvertisement.setStatus(Status.EXPIRED);
            carAdvertisement.setEndPublicationDate(new Timestamp(date.getTime()));
            messageService.sendMessage(carAdvertisement.getUser().getEmail(),
                    MessageTextUtil.getCarAdvertisementExpiredMessageText(carAdvertisement),
                    appProperties.getCarAdvertisementSubject());
        }
        carAdvertisementRepository.saveAll(carAdvertisements);
    }
}
