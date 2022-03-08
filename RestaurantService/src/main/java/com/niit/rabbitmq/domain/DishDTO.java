package com.niit.rabbitmq.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishDTO {
    private int dishId;
    private String dishName;
    private String dishCategory;
    private int dishPrice;

}