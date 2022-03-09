package com.niit.rabbitmq.domain;

import com.niit.model.RestaurantAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishDTO {
    private int restaurantId;
    private String restaurantName;
    private RestaurantAddress restaurantAddress;
    private String typeOfRestaurant;
    private String image;

}
