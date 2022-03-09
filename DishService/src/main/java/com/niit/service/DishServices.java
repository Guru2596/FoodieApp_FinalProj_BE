package com.niit.service;

import com.niit.exception.DishAlreadyExistsException;
import com.niit.model.Dishes;

import java.util.List;

public interface DishServices {
   Dishes addDishesToDb(Dishes dishes) throws DishAlreadyExistsException;
   List<Dishes> viewDishes();
   String deleteDishesById(int id) throws Exception;
}
