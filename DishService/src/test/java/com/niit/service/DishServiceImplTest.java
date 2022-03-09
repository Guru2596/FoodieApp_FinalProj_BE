package com.niit.service;

import com.niit.model.Dishes;
import com.niit.repostiory.DishRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DishServiceImplTest {

    @Mock
    private DishRepository dishRepository;

    @InjectMocks
    private DishServiceImpl dishService;

    private Dishes dishes1,dishes2,dishes3;
    List<Dishes> dishesList;

    @BeforeEach
    void setUp(){

        dishes1=new Dishes(1,"idly",40,"Breakfast");
        dishes2=new Dishes(2,"dosa",40,"Breakfast");
        dishes3=new Dishes(3,"puri",40,"Breakfast");
        dishesList=Arrays.asList(dishes1,dishes2,dishes3);

    }

    @AfterEach
    void tearDown(){
        dishes1=null;
        dishes2=null;
        dishes3=null;
    }

    @Test
    public void givenDishesToSaveReturnSavedDishesSuccess() throws Exception{
        when(dishRepository.findById(dishes1.getDishId())).thenReturn(Optional.empty());
        when(dishRepository.save(any())).thenReturn(dishes1);
        assertEquals(dishes1,dishService.addDishesToDb(dishes1));
        verify(dishRepository,times(1)).save(any());
        verify(dishRepository,times(1)).findById(any());

    }

}
