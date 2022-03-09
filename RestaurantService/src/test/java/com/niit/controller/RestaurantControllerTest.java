package com.niit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.model.Dish;
import com.niit.model.Restaurant;
import com.niit.model.RestaurantAddress;
import com.niit.service.RestaurantServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class RestaurantControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RestaurantServiceImpl restaurantService;
    private Restaurant restaurant1, restaurant2;
    List<Restaurant> restaurantList;
    RestaurantAddress restaurantAddress1, restaurantAddress2;

    Dish dish1 = new Dish(1,"Idly","veg",40);
    Dish dish2 = new Dish(2,"Idly","veg",40);

    @InjectMocks
    private RestaurantController restaurantController;

    @BeforeEach
    public void setUp(){
        List<Dish> list = new ArrayList<>(Arrays.asList(dish1,dish2));
        restaurantAddress1 = new RestaurantAddress("Bangalore","Karanataka",123,99005173L);
        restaurant1 = new Restaurant(1,"Taj",restaurantAddress1,"restaraunt","images", list);
        restaurantList = Arrays.asList(restaurant1);
        mockMvc = MockMvcBuilders.standaloneSetup(restaurantController).build();
    }

    @AfterEach
    public void tearDown(){
        restaurant1 = null;
    }

    @Test
    public void saveRestaurantToDb() throws Exception {
        when(restaurantService.uploadRestaurantToDb(any())).thenReturn(restaurant1);
        mockMvc.perform(post("/api/restaurant/upload-restaurant").contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(restaurant1)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(restaurantService,times(1)).uploadRestaurantToDb(any());
    }

    @Test
    public void givenProductCodeDeleteProduct() throws Exception {
        when(restaurantService.deleteRestaurant(anyInt())).thenReturn(true);
        mockMvc.perform(delete("/api/restaurant/delete-restaurant/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(restaurantService, times(1)).deleteRestaurant(anyInt());

    }

    private static String jsonToString(final Object ob) throws JsonProcessingException {
        String result;

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(ob);
            result = jsonContent;
        } catch(JsonProcessingException e) {
            result = "JSON processing error";
        }

        return result;
    }


}

