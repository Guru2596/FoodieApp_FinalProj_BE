package com.niit.service;

import com.niit.exception.RestaurantAlreadyExistsException;
import com.niit.exception.RestaurantNotfoundException;
import com.niit.model.Favourite;
import com.niit.model.Restaurant;
import com.niit.repository.FavouriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class FavouriteServiceImpl implements FavouriteService{

    @Autowired
    FavouriteRepository favouriteRepository;

//    @Override
//    public Favourite uploadFavoriteToDb(Favourite favourite, int id) throws RestaurantAlreadyExistsException {
//        if(favouriteRepository.findByRestaurantId(id).isPresent()){
//            throw new RestaurantAlreadyExistsException();
//        }
//        return favouriteRepository.save(favourite);
//    }

    @Override
    public Favourite uploadFavoriteToDb(Favourite favourite) throws RestaurantAlreadyExistsException {

        return favouriteRepository.save(favourite);
    }

    @Override
    public List<Favourite> getListOfFavourite() {
        return favouriteRepository.findAll();
    }

    @Override
    public boolean deleteRestaurantFromFavoriteList(String email) throws RestaurantNotfoundException {
        boolean flag = false;
        if(favouriteRepository.findByemail(email).isEmpty())
        {
            throw new RestaurantNotfoundException();
        }
        else {
            favouriteRepository.deleteById(email);
            flag = true;
        }
        return flag;
    }


}
