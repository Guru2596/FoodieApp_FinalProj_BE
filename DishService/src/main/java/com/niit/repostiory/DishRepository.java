package com.niit.repostiory;

import com.niit.model.Dishes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DishRepository extends MongoRepository<Dishes, Integer> {

}
