package com.niit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Dishes {
    @Id
    private int dishId;
    private String dishName;
    private int dishPrice;
    private String dishCategory;

}

