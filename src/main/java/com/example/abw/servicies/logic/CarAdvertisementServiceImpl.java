package com.example.abw.servicies.logic;

import com.example.abw.AppProperties;
import com.example.abw.entities.advertisement.CarAdvertisement;
import com.example.abw.exception.security.PrivacyViolationException;
import com.example.abw.kafka.KafkaProducers;
import com.example.abw.model.advertisement.Status;
import com.example.abw.model.advertisement.car_advertisement.CarAdvertisementMapper;
import com.example.abw.model.advertisement.car_advertisement.CarAdvertisementResponse;
import com.example.abw.model.currency.Currency;
import com.example.abw.model.kafka.KafkaCarAdDTO;
import com.example.abw.model.pageable.PageableParams;
import com.example.abw.model.advertisement.car_advertisement.CarAdvertisementDTOAdd;
import com.example.abw.entities.sell_item.car.CarBrand;
import com.example.abw.entities.user.User;
import com.example.abw.repositories.advertisement.CarAdvertisementRepository;
import com.example.abw.repositories.pagination.CarAdvertisementPaginationRepository;
import com.example.abw.security.CustomUserDetails;
import com.example.abw.security.utils.UserUtil;
import com.example.abw.servicies.*;
import com.example.abw.exception.entities.ResourceNotFoundException;
import com.example.abw.utils.MessageTextUtil;
import com.example.abw.utils.PageableUtil;
import com.example.abw.exception.validation.ValidationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@Service
public class CarAdvertisementServiceImpl extends GenericServiceImpl<CarAdvertisement> implements CarAdvertisementService {

    private final CarAdvertisementRepository carAdvertisementRepository;
    private final CarBrandService carBrandServiceImpl;
    private final UserService userServiceImpl;
    private final CarAdvertisementPaginationRepository carAdvertisementPaginationRepository;
    private final UserUtil userUtil;
    private final CurrencyExchangeService currencyExchangeService;
    private final CarAdvertisementMapper carAdvertisementMapper;
    private final CarImageService carImageService;
    private final KafkaProducers kafkaProducers;
    private final MessageService messageService;
    private final AppProperties appProperties;

    public CarAdvertisementServiceImpl(CarAdvertisementRepository carAdvertisementRepository, UserService userServiceImpl,
                                       CarBrandService carBrandServiceImpl,
                                       CarAdvertisementPaginationRepository carAdvertisementPaginationRepository,
                                       UserUtil userUtil, CurrencyExchangeService currencyExchangeService,
                                       CarAdvertisementMapper carAdvertisementMapper, CarImageService carImageServiceImpl,
                                       KafkaProducers kafkaProducers, MessageService messageService, AppProperties appProperties
    ) {
        super(carAdvertisementRepository, CarAdvertisement.class);
        this.carAdvertisementRepository = carAdvertisementRepository;
        this.userServiceImpl = userServiceImpl;
        this.carBrandServiceImpl = carBrandServiceImpl;
        this.carAdvertisementPaginationRepository = carAdvertisementPaginationRepository;
        this.userUtil = userUtil;
        this.currencyExchangeService = currencyExchangeService;
        this.carAdvertisementMapper = carAdvertisementMapper;
        this.carImageService = carImageServiceImpl;
        this.kafkaProducers = kafkaProducers;
        this.messageService = messageService;
        this.appProperties = appProperties;
    }

    @Override
    public String createCarAdvertisement(CarAdvertisementDTOAdd carAdvertisementDTOAdd)
            throws ValidationException, IOException, ResourceNotFoundException, PrivacyViolationException {
        CustomUserDetails customUserDetails = userUtil.getCustomUserDetails();
        User user = userServiceImpl.findByEmail(customUserDetails.getUsername());
        if (user != null) {
            CarAdvertisement carAd = new CarAdvertisement();
            Date date = new Date();
            Timestamp timestamp = new Timestamp(date.getTime());
            CarBrand carBrand = findCarBrand(carAdvertisementDTOAdd.getCarBrandId());
            carAd.setPublicationDate(timestamp);
            carAd.setCarBrand(carBrand);
            carAd.setDescriptions(carAdvertisementDTOAdd.getDescriptions());
            carAd.setPrice(carAdvertisementDTOAdd.getPrice());
            carAd.setPriceCurrency(carAdvertisementDTOAdd.getCurrency());
            carAd.setUser(user);
            carAd.setStatus(Status.ON_CHECK);
            carAd = create(carAd);
            kafkaProducers.sendCarAd(carAd);
            carImageService.createCarAdImages(carAdvertisementDTOAdd.getImages(), carAd.getId());
            return MessageTextUtil.getCarAdOnCheckMessageText(carAd);
        }
        throw new ResourceNotFoundException("user not found");
    }

    @Transactional
    @Override
    public void confirmCarAdvertisement(KafkaCarAdDTO kafkaCarAdDTO) throws ResourceNotFoundException {
        CarAdvertisement carAdvertisement = findById(kafkaCarAdDTO.getId());
        if (kafkaCarAdDTO.isCorrect()) {
            carAdvertisement.setStatus(Status.ACTIVE);
            messageService.sendMessage(carAdvertisement.getUser().getEmail(),
                    MessageTextUtil.getCarAdValidMessageText(carAdvertisement),
                    appProperties.getCarAdvertisementSubject());
        } else {
            carAdvertisement.setStatus(Status.NOT_VALID);
            messageService.sendMessage(carAdvertisement.getUser().getEmail(),
                    MessageTextUtil.getCarAdNotValidMessageText(carAdvertisement, kafkaCarAdDTO.getErrorMessage()),
                    appProperties.getCarAdvertisementSubject());
        }
        carAdvertisementRepository.save(carAdvertisement);
    }

    @Transactional
    @Override
    public String updateCarAdvertisement(CarAdvertisementDTOAdd carAdvertisementDTOAdd, long id)
            throws ValidationException, ResourceNotFoundException, PrivacyViolationException {
        CarAdvertisement carAd = findById(id);
        if (userUtil.getCustomUserDetails().getUsername().equals(carAd.getUser().getEmail())) {
            CarBrand carBrand = findCarBrand(carAdvertisementDTOAdd.getCarBrandId());
            carAd.setCarBrand(carBrand);
            carAd.setDescriptions(carAdvertisementDTOAdd.getDescriptions());
            carAd.setPrice(carAdvertisementDTOAdd.getPrice());
            carAd.setPriceCurrency(carAdvertisementDTOAdd.getCurrency());
            carAd.setStatus(Status.ON_CHECK);
            update(carAd, id);
            kafkaProducers.sendCarAd(carAd);
            return MessageTextUtil.getCarAdOnCheckMessageText(carAd);
        }
        throw new PrivacyViolationException("privacy violation");

    }

    @Transactional
    @Override
    public List<CarAdvertisementResponse> findAllByCarBrand(String carBrand, boolean isAdmin, PageableParams pageableParams) throws ValidationException {
        Pageable pageable = PageableUtil.getPageable(pageableParams);
        List<CarAdvertisement> carAdvertisements;
        if (isAdmin) {
            carAdvertisements = carAdvertisementPaginationRepository.findByCarBrand_Name(carBrand, pageable);
        } else {
            carAdvertisements = carAdvertisementPaginationRepository
                    .findByCarBrand_NameAndStatus(carBrand, Status.ACTIVE, pageable);
        }
        return getListResponse(pageableParams.getCurrency(), carAdvertisements);
    }


    @Transactional
    @Override
    public List<CarAdvertisementResponse> findAllByCarBrandName(String carBrandName,
                                                                boolean isAdmin, PageableParams pageableParams) throws ValidationException {
        Pageable pageable = PageableUtil.getPageable(pageableParams);
        List<CarAdvertisement> carAdvertisements;
        if (isAdmin) {
            carAdvertisements = carAdvertisementPaginationRepository
                    .findByCarBrand_CarBrandName_Name(carBrandName, pageable);
        } else {
            carAdvertisements = carAdvertisementPaginationRepository
                    .findByCarBrand_CarBrandName_NameAndStatus(carBrandName, Status.ACTIVE, pageable);
        }
        return getListResponse(pageableParams.getCurrency(), carAdvertisements);
    }

    @Transactional
    @Override
    public List<CarAdvertisementResponse> findAllByPrice(
            Long startPrice, Long endPrice, boolean isAdmin, PageableParams pageableParams) throws ValidationException {
        Pageable pageable = PageableUtil.getPageable(pageableParams);
        List<CarAdvertisement> carAdvertisements;
        if (isAdmin) carAdvertisements = carAdvertisementPaginationRepository.
                readAllByPriceBetween(startPrice, endPrice, pageable);
        else carAdvertisements = carAdvertisementPaginationRepository.
                readAllByPriceBetweenAndStatus(startPrice, endPrice, Status.ACTIVE, pageable);
        return getListResponse(pageableParams.getCurrency(), carAdvertisements);
    }

    @Transactional
    @Override
    public List<CarAdvertisementResponse> findAllByPriceAndCarBrand(Long startPrice, Long endPrice, String carBrand,
                                                                    boolean isAdmin, PageableParams pageableParams) throws ValidationException {
        Pageable pageable = PageableUtil.getPageable(pageableParams);
        List<CarAdvertisement> carAdvertisements;
        if (isAdmin) {
            carAdvertisements = carAdvertisementPaginationRepository
                    .readAllByPriceBetweenAndCarBrand_Name(startPrice, endPrice, carBrand, pageable);
        } else carAdvertisements = carAdvertisementPaginationRepository
                .readAllByPriceBetweenAndStatusAndCarBrand_Name(
                        startPrice, endPrice, Status.ACTIVE, carBrand, pageable);
        return getListResponse(pageableParams.getCurrency(), carAdvertisements);
    }

    @Transactional
    @Override
    public List<CarAdvertisementResponse> findAllByPriceAndCarBrandName(Long startPrice, Long endPrice,
                                                                        String carBrandName,
                                                                        boolean isAdmin,
                                                                        PageableParams pageableParams) throws ValidationException {
        Pageable pageable = PageableUtil.getPageable(pageableParams);
        List<CarAdvertisement> carAdvertisements;
        if (isAdmin) carAdvertisements = carAdvertisementPaginationRepository
                .readAllByPriceBetweenAndCarBrand_CarBrandName_Name(startPrice, endPrice,
                        carBrandName, pageable);
        else carAdvertisements = carAdvertisementPaginationRepository
                .readAllByPriceBetweenAndStatusAndCarBrand_CarBrandName_Name(startPrice, endPrice,
                        Status.ACTIVE, carBrandName, pageable);
        return getListResponse(pageableParams.getCurrency(), carAdvertisements);
    }

    @Transactional
    @Override
    public List<CarAdvertisementResponse> findAll(boolean isAdmin, PageableParams pageableParams) throws ValidationException {
        Pageable pageable = PageableUtil.getPageable(pageableParams);
        List<CarAdvertisement> carAdvertisements;
        if (isAdmin) carAdvertisements = findAllCarAdvertisements(pageable);
        else carAdvertisements = carAdvertisementPaginationRepository.readAllByStatus(Status.ACTIVE);
        return getListResponse(pageableParams.getCurrency(), carAdvertisements);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void softDelete(long id)
            throws ResourceNotFoundException, ValidationException, PrivacyViolationException {
        CarAdvertisement carAdvertisement = findById(id);
        if (userUtil.getCustomUserDetails().getUsername() != null) {
            if (userUtil.getCustomUserDetails().getUsername().equals(carAdvertisement.getUser().getEmail())) {
                carAdvertisement.setStatus(Status.SOLD);
                Date date = new Date();
                Timestamp endPublicationDate = new Timestamp(date.getTime());
                carAdvertisement.setEndPublicationDate(endPublicationDate);
                carAdvertisementRepository.save(carAdvertisement);
            } else throw new PrivacyViolationException("privacy violation");
        }
    }


    @Override
    public CarAdvertisementResponse findAdvertisement(long advertisementId, Currency currency)
            throws ResourceNotFoundException {
        CarAdvertisement carAdvertisement = findById(advertisementId);
        if (carAdvertisement.getStatus().equals(Status.ACTIVE)) {
            CarAdvertisementResponse carAdvertisementResponse = carAdvertisementMapper
                    .carAdvertisementToCarAdvertisementResponse(carAdvertisement);
            carAdvertisementResponse.setPriceCurrency(currency);
            carAdvertisementResponse.setPrice(currencyExchangeService.getPrice(
                    carAdvertisement.getPriceCurrency(), currency, carAdvertisement.getPrice()));
            return carAdvertisementResponse;
        }
        throw new ResourceNotFoundException("advertisement not found");
    }

    @Override
    public CarAdvertisementResponse findUserAdvertisement(long advertisementId)
            throws ResourceNotFoundException, PrivacyViolationException {
        CarAdvertisement carAdvertisement = findById(advertisementId);
        if (userUtil.getCustomUserDetails().getUsername().equals(carAdvertisement.getUser().getEmail()))
            return carAdvertisementMapper.carAdvertisementToCarAdvertisementResponse(carAdvertisement);
        throw new PrivacyViolationException("privacy violation");
    }

    @Transactional
    @Override
    public List<CarAdvertisementResponse> findAllByUser(
            PageableParams pageableParams) throws ResourceNotFoundException, ValidationException {
        Pageable pageable = PageableUtil.getPageable(pageableParams);
        CustomUserDetails customUserDetails = userUtil.getCustomUserDetails();
        if (customUserDetails != null) {
            User user = userServiceImpl.findByEmail(customUserDetails.getUsername());
            List<CarAdvertisement> carAdvertisements =
                    carAdvertisementPaginationRepository.readAllByUser(user, pageable);
            return getListResponse(pageableParams.getCurrency(), carAdvertisements);
        }
        throw new ResourceNotFoundException("user not found");
    }

    @Override
    public CarAdvertisementResponse refreshCarAdvertisement(long advertisementId)
            throws ResourceNotFoundException, PrivacyViolationException {
        CarAdvertisement carAdvertisement = findById(advertisementId);
        if (userUtil.getCustomUserDetails().getUsername().equals(carAdvertisement.getUser().getEmail())) {
            Date date = new Date();
            Timestamp currentTime = new Timestamp(date.getTime());
            carAdvertisement.setPublicationDate(currentTime);
            carAdvertisement.setEndPublicationDate(null);
            carAdvertisement.setStatus(Status.ACTIVE);
            return carAdvertisementMapper.carAdvertisementToCarAdvertisementResponse(
                    carAdvertisementRepository.save(carAdvertisement)
            );
        }
        throw new PrivacyViolationException("privacy violation");
    }

    private CarBrand findCarBrand(long id) throws ValidationException {
        try {
            return carBrandServiceImpl.findById(id);
        } catch (ResourceNotFoundException e) {
            throw new ValidationException("carBrand not found");
        }
    }

    private List<CarAdvertisement> findAllCarAdvertisements(Pageable pageable) {
        List<CarAdvertisement> result = new ArrayList<>();
        Iterable<CarAdvertisement> iterable = carAdvertisementPaginationRepository.findAll(pageable);
        Iterator<CarAdvertisement> iterator = iterable.iterator();
        if (iterator.hasNext()) iterator.forEachRemaining(result::add);
        return result;
    }

    private List<CarAdvertisementResponse> getListResponse(
            Currency currencyTo, List<CarAdvertisement> carAdvertisements) {
        List<CarAdvertisementResponse> carAdvertisementResponses =
                carAdvertisementMapper.toCarAdvertisementResponse(carAdvertisements);
        for (CarAdvertisementResponse carAdvertisementResponse : carAdvertisementResponses) {
            carAdvertisementResponse.setPrice(
                    currencyExchangeService.getPrice(carAdvertisementResponse.getPriceCurrency(), currencyTo,
                            carAdvertisementResponse.getPrice()));
            carAdvertisementResponse.setPriceCurrency(currencyTo);
        }
        return carAdvertisementResponses;
    }
}
