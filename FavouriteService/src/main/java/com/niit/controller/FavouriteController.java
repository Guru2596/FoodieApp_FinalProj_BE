package com.niit.controller;

import com.niit.exception.RestaurantAlreadyExistsException;
import com.niit.exception.RestaurantNotfoundException;
import com.niit.model.Favourite;
import com.niit.service.FavouriteService;
import com.niit.service.FavouriteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favourite")
public class FavouriteController {

    @Autowired
    FavouriteService favouriteService;

//    @PostMapping("/add-to-favourite/{id}")
//    public Favourite addToFavorite(@PathVariable int id, @RequestBody Favourite favourite) throws RestaurantAlreadyExistsException {
//        return favouriteService.uploadFavoriteToDb(favourite,id);
//    }

    @PostMapping("/add-to-favourite")
    public Favourite addToFavorite(@RequestBody Favourite favourite) throws RestaurantAlreadyExistsException {
        return favouriteService.uploadFavoriteToDb(favourite);
    }

    @GetMapping("/restaurant-list")
    public ResponseEntity<List<Favourite>> getListOfFavourite(){
        return new ResponseEntity<List<Favourite>>(favouriteService.getListOfFavourite(), HttpStatus.OK);
    }

    /*@DeleteMapping("/delete-restaurant/{email}")
    public ResponseEntity<?> deleteRestaurantFromFavoriteById(@PathVariable String email) throws RestaurantNotfoundException {
        return new ResponseEntity<>(  favouriteService.deleteRestaurantFromFavoriteList(email), HttpStatus.OK);
    }*/

}
