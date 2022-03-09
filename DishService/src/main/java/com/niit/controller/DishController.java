package com.niit.controller;


import com.niit.exception.DishAlreadyExistsException;
import com.niit.model.Dish;
import com.niit.service.DishServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/dish")
public class DishController {

    @Autowired
    DishServices dishServices;

    @PostMapping("/addDishes")
    public Dish addDishes(@RequestBody Dish dishes) throws DishAlreadyExistsException {
        return dishServices.addDishesToDb(dishes);
    }
//    public Dishes searchDishesById(@PathVariable int id){
//        return DishServices.searchDishesById(id).get();
//    }
@GetMapping("/getdishes")
public List<Dish> getDishes()  {
    List<Dish> dish = new ArrayList<>();
    dish= dishServices.viewDishes();
    return dish;
}
    @DeleteMapping("/deletedishbyid/{id}")
    public ResponseEntity<?> deleteDishesById(@PathVariable int id) throws Exception {
        return new ResponseEntity<>(dishServices.deleteDishesById(id), HttpStatus.OK);
    }

}
