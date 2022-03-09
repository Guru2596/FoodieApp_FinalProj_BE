package com.niit.service;


import com.niit.model.Dishes;
import com.niit.repostiory.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishServices {
    @Autowired
    DishRepository dishRepository;

    @Override
    public Dishes addDishesToDb(Dishes dishes) {
        return dishRepository.save(dishes);
    }

    @Override
    public List<Dishes> viewDishes() {
        return dishRepository.findAll();
    }

//    @Override
//    public String deleteDishesById(int id) {
//        return null;
//    }

//    @Override
//    public boolean deleteDishesById(int id) throws Exception {
//        boolean flag;
//        if(dishRepository.findById(id).isEmpty())
//        {
//            throw new Exception();
//        }
//        else {
//            dishRepository.deleteById(id);
//            flag = true;
//        }
//        return flag;
//    }

    public String deleteDishesById(int id) throws Exception{
        String message;
        try {
            dishRepository.deleteById(id);
            message = "Content Removed";
        } catch (Exception exception) {
            message = "Content Not Removed";
        }
        return message;
    }
}
