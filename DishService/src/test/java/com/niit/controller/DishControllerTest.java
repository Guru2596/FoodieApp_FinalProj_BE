package com.niit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.exception.DishAlreadyExistsException;
import com.niit.model.Dishes;
import com.niit.service.DishServiceImpl;
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

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class DishControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private DishServiceImpl dishService;
    private Dishes dishes1, dishes2;
    List<Dishes> dishesList;

    @InjectMocks
    private DishController dishController;

    @BeforeEach
    void setUp() {
        dishes1 = new Dishes(1,"idly",40,"Breakfast");
        dishes2 = new Dishes(1,"dosa",40,"Breakfast");
        dishesList= Arrays.asList(dishes1,dishes2);
        mockMvc = MockMvcBuilders.standaloneSetup(dishController).build();

    }

    @AfterEach
    void tearDown() {
        dishes1=null;
        dishes2=null;
    }

    @Test
    public void givenCustomerToSaveReturnSaveProductSuccess() throws Exception {
        when(dishService.addDishesToDb(any())).thenReturn(dishes1);
        mockMvc.perform(post("/api/dish/addDishes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(dishes1)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(dishService,times(1)).addDishesToDb(any());

    }

    @Test
    public void givenProductToSaveReturnSaveProductFailure() throws Exception {
        when(dishService.addDishesToDb(any())).thenThrow(DishAlreadyExistsException.class);
        mockMvc.perform(post("/api/dish/addDishes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(dishes1)))
                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
        verify(dishService,times(1)).addDishesToDb(any());

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
