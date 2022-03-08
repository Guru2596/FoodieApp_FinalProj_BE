package com.niit.service;

import com.niit.exception.RestaurantAlreadyExistsException;
import com.niit.exception.RestaurantNotfoundException;
import com.niit.model.Restaurant;
import com.niit.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    RestaurantRepository restaurantRepository;

    public Restaurant uploadRestaurantToDb(Restaurant restaurant) throws RestaurantAlreadyExistsException {
        if(restaurantRepository.findById(restaurant.getRestaurantId()).isPresent()){
            throw new RestaurantAlreadyExistsException();
        }
        return restaurantRepository.save(restaurant);
    }

    @Override
    public boolean deleteRestaurant(int id) throws RestaurantNotfoundException {
        boolean flag;
        if(restaurantRepository.findById(id).isEmpty())
        {
            throw new RestaurantNotfoundException();
        }
        else {
            restaurantRepository.deleteById(id);
            flag = true;
        }
        return flag;
    }

    @Override
    public List<Restaurant> getListOfRestaurants() {
        return restaurantRepository.findAll();
    }
}
