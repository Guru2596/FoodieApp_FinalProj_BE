package com.niit.repository;

import com.niit.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository  extends MongoRepository<Restaurant,Integer> {
}
