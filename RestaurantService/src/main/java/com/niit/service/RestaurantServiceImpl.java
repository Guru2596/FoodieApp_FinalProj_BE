package com.niit.service;

import com.niit.exception.RestaurantAlreadyExistsException;
import com.niit.exception.RestaurantNotfoundException;
import com.niit.model.Dish;
import com.niit.model.Restaurant;
import com.niit.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Restaurant uploadDishesToDb(Dish dish, int restaurantId) throws RestaurantNotfoundException {
        if(restaurantRepository.findById(restaurantId).isEmpty())
        {
            throw new RestaurantNotfoundException();
        }
        Restaurant restaurant = restaurantRepository.findByRestaurantId(restaurantId);
        if(restaurant.getDishList() == null) {
            restaurant.setDishList(Arrays.asList(dish));
        }
        else {
            List<Dish> dishList = restaurant.getDishList();
            dishList.add(dish);
            restaurant.setDishList(dishList);
        }
      return restaurantRepository.save(restaurant);
    }
}
