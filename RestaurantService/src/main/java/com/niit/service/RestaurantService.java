package com.niit.service;

import com.niit.exception.RestaurantAlreadyExistsException;
import com.niit.exception.RestaurantNotfoundException;
import com.niit.model.Restaurant;
import java.util.List;

public interface RestaurantService {
     Restaurant uploadRestaurantToDb(Restaurant restaurant) throws RestaurantAlreadyExistsException;
     boolean deleteRestaurant(int id) throws RestaurantNotfoundException;
     List<Restaurant> getListOfRestaurants();
}
