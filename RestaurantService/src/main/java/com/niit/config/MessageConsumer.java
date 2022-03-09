package com.niit.config;

import com.niit.model.Dish;
import com.niit.rabbitmq.domain.DishDTO;
import com.niit.service.RestaurantServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

public class MessageConsumer {
    @Autowired
    private RestaurantServiceImpl restaurantService;
    @RabbitListener(queues = "dishes_queue")
    public void getCustomerFromRabbitMQ(DishDTO dishDTO){
        try {
            Dish dish = new Dish(dishDTO.getDishId(), dishDTO.getDishName(), dishDTO.getDishCategory(), dishDTO.getDishPrice());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
