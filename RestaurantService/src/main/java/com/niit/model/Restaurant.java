package com.niit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Restaurant {
    @Id
    private int restaurantId;
    private String restaurantName;
    private RestaurantAddress restaurantAddress;
    private String typeOfRestaurant;
    private String image;
    private List<Dish> dishList;

}
