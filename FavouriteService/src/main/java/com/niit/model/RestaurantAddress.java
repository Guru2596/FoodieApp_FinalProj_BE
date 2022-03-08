package com.niit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantAddress {
    private String city;
    private String state;
    private int pinCode;
    private Long phoneNumber;

}
