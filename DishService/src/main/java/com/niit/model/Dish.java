package com.niit.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dish {

    private int dishId;
    private String dishName;
    private String dishCategory;
    private int dishPrice;

}
