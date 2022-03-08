package com.niit.service;

import com.niit.model.Customer;
import com.niit.repository.CustomerAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerAuthService {

    @Autowired
    private CustomerAuthRepository repository;
    public Customer saveCustomer(Customer customer){
        return repository.save(customer);
    }
    public Customer getCustomerByEmailId(String email){return repository.findByEmailId(email);}

    public Customer getCustomerByEmailIdAndUserPassword(String email, String password){
        return repository.findByEmailIdAndPassword(email,password);
    }
}
