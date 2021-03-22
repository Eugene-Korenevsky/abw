package com.example.abw.servicies.user;


import com.example.abw.entities.user.User;
import com.example.abw.repositories.user.UserRepository;
import com.example.abw.servicies.UserService;
import com.example.abw.servicies.exceptions.ResourceNotFoundException;
import com.example.abw.servicies.logic.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class UserServiceTest {

    // @Mock
    // AppProperties appProperties;
    // @Mock
    // CarAdPaginationRepository carAdPaginationRepository;
    //  @Mock
    // CarAdRepository carAdRepository;
    @Mock
    UserRepository userRepository;
    // @Mock
    // CarBrandRepository carBrandRepository;


    //  @InjectMocks
    // CarAdPagBetweenPrice carAdPagBetweenPrice = new CarAdPagBetweenPrice();

    @InjectMocks
    UserService userService = new UserServiceImpl(userRepository);
    // CarBrandService carBrandService = new CarBrandServiceImpl(carBrandRepository);

//    CarAdService carAdService = new CarAdServiceImpl(carAdRepository, userService, carBrandService);

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void userPagination() throws ResourceNotFoundException {
        User user = new User();
        user.setPassword("admin");
        user.setPhoneNumber("+375297320231");
        user.setName("Eugene");
        user.setEmail("admin@tut.by");
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        User emp = userService.findById(1L);
        assertEquals("admin", emp.getPassword());
      /*  List<Ad> ads = carAdPagBetweenPrice.getPaginationResult(300000L, 500000L, 0, 30,
                "price", SortKind.DESC, false);
        for (Ad ad : ads) {
            assertTrue(ad.isSold());
            System.out.println(ad.getPublicationDate());
        }*/
    }


}
