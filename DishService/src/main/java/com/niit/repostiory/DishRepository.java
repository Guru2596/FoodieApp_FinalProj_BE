package com.niit.repostiory;

import com.niit.model.Dish;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DishRepository extends MongoRepository<Dish, Integer> {
}
