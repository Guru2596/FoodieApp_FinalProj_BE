package com.niit.controller;

import com.niit.exception.RestaurantAlreadyExistsException;
import com.niit.exception.RestaurantNotfoundException;
import com.niit.model.Restaurant;
import com.niit.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @PostMapping("/upload-restaurant")
    public Restaurant uploadRestaurant(@RequestBody Restaurant restaurant) throws RestaurantAlreadyExistsException {
        return restaurantService.uploadRestaurantToDb(restaurant);
    }

    @DeleteMapping("/delete-restaurant/{id}")
    public ResponseEntity<?> deleteRestaurantById(@PathVariable int id) throws RestaurantNotfoundException {
        return new ResponseEntity<>(  restaurantService.deleteRestaurant(id), HttpStatus.OK);
    }

    @GetMapping("/restaurant-list")
    public ResponseEntity<List<Restaurant>> getListOfRestaurant() {
        return new ResponseEntity<List<Restaurant>>(restaurantService.getListOfRestaurants(), HttpStatus.OK);
    }

}
