package com.niit.service;

import com.niit.exception.RestaurantAlreadyExistsException;
import com.niit.model.Dish;
import com.niit.model.Restaurant;
import com.niit.model.RestaurantAddress;
import com.niit.repository.RestaurantRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantServiceImpl restaurantService;
    private Restaurant restaurant1, restaurant2, restaurant3;
    Restaurant restaurantList;
    RestaurantAddress restaurantAddress1, restaurantAddress2, restaurantAddress3;

    Dish dish1 = new Dish(1,"Idly","veg",40);
    Dish dish2 = new Dish(1,"Idly","veg",40);

    @BeforeEach
    void setUp(){
        List<Dish> list = new ArrayList<>(Arrays.asList(dish1,dish2));
        restaurantAddress1 = new RestaurantAddress("Bangalore","Karanataka",123,99005173L);
        restaurant1 = new Restaurant(1,"Taj",restaurantAddress1,"restaraunt","images", list);
    }

    @AfterEach
    void tearDown(){
        restaurant1 = null;
        restaurant2 = null;
    }

    @Test
    public void givenRestaurantToSaveRestaurant() throws RestaurantAlreadyExistsException {
        when(restaurantRepository.findById(restaurant1.getRestaurantId())).thenReturn(Optional.empty());
        when(restaurantRepository.save(any())).thenReturn(restaurant1);
        assertEquals(restaurant1,restaurantService.uploadRestaurantToDb(restaurant1));
        verify(restaurantRepository,times(1)).save(any());
        verify(restaurantRepository,times(1)).findById(any());
    }

    @Test
    public void givenRestaurantToSaveReturnRestaurantFailure(){
        when(restaurantRepository.findById(restaurant1.getRestaurantId())).thenReturn(Optional.ofNullable(restaurant1));
        assertThrows(RestaurantAlreadyExistsException.class,()->restaurantService.uploadRestaurantToDb(restaurant1));
        verify(restaurantRepository,times(0)).save(any());
        verify(restaurantRepository,times(1)).findById(any());
    }
}
