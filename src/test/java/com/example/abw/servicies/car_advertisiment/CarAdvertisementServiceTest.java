package com.example.abw.servicies.car_advertisiment;

import com.example.abw.AppProperties;
import com.example.abw.entities.advertisement.CarAdvertisement;
import com.example.abw.entities.user.User;
import com.example.abw.exception.entities.ResourceNotFoundException;
import com.example.abw.exception.security.PrivacyViolationException;
import com.example.abw.exception.validation.ValidationException;
import com.example.abw.kafka.KafkaProducers;
import com.example.abw.model.advertisement.car_advertisement.CarAdvertisementMapper;
import com.example.abw.model.advertisement.car_advertisement.CarAdvertisementMapperImpl;
import com.example.abw.model.kafka.KafkaCarAdMapper;
import com.example.abw.model.kafka.KafkaCarAdMapperImpl;
import com.example.abw.repositories.advertisement.CarAdvertisementRepository;
import com.example.abw.repositories.pagination.CarAdvertisementPaginationRepository;
import com.example.abw.security.CustomUserDetails;
import com.example.abw.security.utils.UserUtil;
import com.example.abw.servicies.*;
import com.example.abw.servicies.logic.CarAdvertisementServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class CarAdvertisementServiceTest {

    @Mock
    CarAdvertisementRepository carAdvertisementRepository;
    @Mock
    CarBrandService carBrandServiceImpl;
    @Mock
    UserService userServiceImpl;
    @Mock
    CarAdvertisementPaginationRepository carAdvertisementPaginationRepository;
    @Mock
    UserUtil userUtil = new UserUtil();
    @Mock
    CurrencyExchangeService currencyExchangeServiceImpl;
    @Mock
    CarImageService carImageServiceImpl;
    @Mock
    KafkaProducers kafkaProducers;
    @Mock
    MessageService messageService;

    CarAdvertisementMapper carAdvertisementMapper = new CarAdvertisementMapperImpl();

    KafkaCarAdMapper kafkaCarAdMapper = new KafkaCarAdMapperImpl();

    KafkaCarAdMapper carAdMapper = new KafkaCarAdMapperImpl();


    private CarAdvertisementService carAdvertisementService;

    private AutoCloseable closeable;


    @Before
    public void init() {
        AppProperties appProperties = new AppProperties();
        closeable = MockitoAnnotations.openMocks(this);
        carAdvertisementService = new CarAdvertisementServiceImpl(carAdvertisementRepository,
                userServiceImpl, carBrandServiceImpl, carAdvertisementPaginationRepository, userUtil,
                currencyExchangeServiceImpl, carAdvertisementMapper, carImageServiceImpl, kafkaProducers,
                messageService, appProperties, kafkaCarAdMapper);
    }

    @After
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    public void softDeleteTest() throws ValidationException, PrivacyViolationException, ResourceNotFoundException {
        CarAdvertisement carAdvertisement = new CarAdvertisement();
        carAdvertisement.setPrice(BigDecimal.valueOf(12.22));
        carAdvertisement.setId(1L);
        User user = new User();
        user.setEmail("vanya@email.com");
        carAdvertisement.setUser(user);
        CustomUserDetails customUserDetails = Mockito.mock(CustomUserDetails.class);
        Mockito.when(carAdvertisementRepository.findById(1L)).thenReturn(java.util.Optional.of(carAdvertisement));
        Mockito.when(customUserDetails.getUsername()).thenReturn("vanya@email.com");
        Mockito.when(userUtil.getCustomUserDetails()).thenReturn(customUserDetails);
        carAdvertisementService.softDelete(1L);
        Mockito.verify(carAdvertisementRepository, Mockito.atLeastOnce()).save(Mockito.any());
        Mockito.verify(carAdvertisementRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(customUserDetails, Mockito.times(2)).getUsername();
        Mockito.verify(userUtil, Mockito.times(2)).getCustomUserDetails();
    }

    @Test
    public void testFindById() throws ResourceNotFoundException {
        CarAdvertisement carAdvertisement = new CarAdvertisement();
        carAdvertisement.setPrice(BigDecimal.valueOf(12.22));
        Mockito.when(carAdvertisementRepository.findById(1L)).thenReturn(java.util.Optional.of(carAdvertisement));
        Assert.assertEquals(BigDecimal.valueOf(12.22), carAdvertisementService.findById(1L).getPrice());
        Mockito.verify(carAdvertisementRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    public void testFindByPrice() throws ValidationException {
        try {
            carAdvertisementService.findAllByPrice(0L, 1000L, false, null);
        } catch (ValidationException e) {
            Assert.assertNotEquals("", e.getFullMessage());
        }
        Mockito.verify(carAdvertisementPaginationRepository, Mockito.never())
                .readAllByPriceBetween(0L, 1000L, null);
    }
}
