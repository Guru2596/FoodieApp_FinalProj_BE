package com.niit.repository;

import com.niit.model.Favourite;
import com.niit.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavouriteRepository extends MongoRepository<Favourite,String> {

//    @Query("{'restaurantList.restaurantId' : {$in : [?0]}}")
//    Optional<Favourite> findByRestaurantId(int id);

    List<Favourite> findByemail(String email);
}
