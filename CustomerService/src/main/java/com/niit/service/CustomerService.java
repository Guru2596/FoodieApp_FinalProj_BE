package com.niit.service;

import com.niit.exception.CustomerAlreadyExistsException;
import com.niit.exception.InvalidCredentialsException;
import com.niit.model.Customer;


public interface CustomerService {

    Customer saveCustomer(Customer customer) throws CustomerAlreadyExistsException;
    Customer getCustomerByEmailIdAndUserPassword(String email, String password)throws InvalidCredentialsException;
}
