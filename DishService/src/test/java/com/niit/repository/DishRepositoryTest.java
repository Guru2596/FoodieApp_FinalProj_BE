package com.niit.repository;

import com.niit.model.Dish;
import com.niit.repostiory.DishRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class DishRepositoryTest {

    private Dish dishes;

    @Autowired
    private DishRepository dishRepository;

    @BeforeEach
    void setUp() {
        dishes = new Dish(1,"idly",40,"Breakfast");
    }



    @AfterEach
    void tearDown() {
        dishes = null;
        dishRepository.deleteAll();
    }
    @Test
    public void givenProductToDeleteShouldDeleteProduct(){
        dishRepository.insert(dishes);
        Dish product1 = dishRepository.findById(dishes.getDishId()).get();

        dishRepository.delete(product1);
        assertEquals(Optional.empty(),dishRepository.findById(dishes.getDishId()));


    }
}