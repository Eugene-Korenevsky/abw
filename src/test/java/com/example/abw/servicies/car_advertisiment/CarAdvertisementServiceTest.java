package com.example.abw.servicies.car_advertisiment;

import com.example.abw.entities.advertisement.CarAdvertisement;
import com.example.abw.exception.entities.ResourceNotFoundException;
import com.example.abw.exception.validation.ValidationException;
import com.example.abw.model.advertisement.car_advertisement.CarAdvertisementMapper;
import com.example.abw.repositories.advertisement.CarAdvertisementRepository;
import com.example.abw.repositories.pagination.advertisement.car_advertisement.CarAdvertisementPaginationRepository;
import com.example.abw.security.utils.UserUtil;
import com.example.abw.servicies.CarAdvertisementService;
import com.example.abw.servicies.CarBrandService;
import com.example.abw.servicies.CurrencyExchangeService;
import com.example.abw.servicies.UserService;
import com.example.abw.servicies.logic.CarAdvertisementServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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
    UserUtil userUtil;
    @Mock
    CurrencyExchangeService currencyExchangeServiceImpl;
    @Mock
    CarAdvertisementMapper carAdvertisementMapper;
    @InjectMocks
    CarAdvertisementService carAdvertisementService = new CarAdvertisementServiceImpl(carAdvertisementRepository,
            userServiceImpl,carBrandServiceImpl,carAdvertisementPaginationRepository,userUtil,
            currencyExchangeServiceImpl,carAdvertisementMapper);

    //private CarAdvertisementService carAdvertisementService;


    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        //MockitoAnnotations.initMocks(this);
       // carAdvertisementService = new CarAdvertisementServiceImpl(carAdvertisementRepository,
         //       userServiceImpl, carBrandServiceImpl, carAdvertisementPaginationRepository, userUtil,
           //     currencyExchangeServiceImpl, carAdvertisementMapper);
    }

    @Test
    public void testFindById() throws ResourceNotFoundException {
        CarAdvertisement carAdvertisement = new CarAdvertisement();
        carAdvertisement.setPrice(BigDecimal.valueOf(12.22));
        Mockito.when(carAdvertisementRepository.findById(1L)).thenReturn(java.util.Optional.of(carAdvertisement));
        Assert.assertEquals(BigDecimal.valueOf(12.22), carAdvertisementService.findById(1L).getPrice());
    }

    @Test
    public void testFindByPrice() throws ValidationException {
        try {
            carAdvertisementService.findAllByPrice(0L,1000L,false,null);
        }catch (ValidationException e){
          Assert.assertNotEquals("",e.getFullMessage());
        }
    }


}
