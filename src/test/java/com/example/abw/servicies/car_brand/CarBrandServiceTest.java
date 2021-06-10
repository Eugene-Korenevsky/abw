package com.example.abw.servicies.car_brand;

import com.example.abw.entities.sell_item.car.CarBrand;
import com.example.abw.exception.entities.ResourceNotFoundException;
import com.example.abw.repositories.sell_item.car.CarBrandRepository;
import com.example.abw.servicies.CarBrandService;
import com.example.abw.servicies.logic.CarBrandServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class CarBrandServiceTest {

    @Mock
    CarBrandRepository carBrandRepository;

    @InjectMocks
    CarBrandService carBrandServiceImpl = new CarBrandServiceImpl(carBrandRepository);

    private AutoCloseable closeable;

    @Before public void openMocks() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @After
    public void releaseMocks() throws Exception {
        closeable.close();
    }

    @Test
    public void test() throws ResourceNotFoundException {
        CarBrand carBrand = new CarBrand();
        carBrand.setName("80B4");
        Mockito.when(carBrandRepository.findById(1L)).thenReturn(java.util.Optional.of(carBrand));
        Assert.assertEquals("80B4",carBrandServiceImpl.findById(1L).getName());
        Mockito.verify(carBrandRepository,Mockito.times(1)).findById(1L);
    }
}
