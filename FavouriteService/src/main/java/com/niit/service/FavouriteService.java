package com.niit.service;

import com.niit.exception.RestaurantAlreadyExistsException;
import com.niit.exception.RestaurantNotfoundException;
import com.niit.model.Favourite;

import java.util.List;

public interface FavouriteService {

    //Favourite uploadFavoriteToDb(Favourite favourite,int id) throws RestaurantAlreadyExistsException;
    Favourite uploadFavoriteToDb(Favourite favourite) throws RestaurantAlreadyExistsException;
    List<Favourite> getListOfFavourite();

    boolean deleteRestaurantFromFavoriteList(String email) throws RestaurantNotfoundException;
}
