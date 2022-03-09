package com.niit.service;

import com.niit.exception.DishAlreadyExistsException;
import com.niit.model.Dish;

import java.util.List;

public interface DishServices {
   Dish addDishesToDb(Dish dishes) throws DishAlreadyExistsException;
   List<Dish> viewDishes();
   String deleteDishesById(int id) throws Exception;
}
