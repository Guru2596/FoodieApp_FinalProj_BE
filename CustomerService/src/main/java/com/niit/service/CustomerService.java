package com.niit.service;

import com.niit.model.Customer;
import com.niit.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Service;

@Service
@EnableEurekaClient
public class CustomerService {
    @Autowired
    private CustomerRepository repository;

    public Customer saveCustomer(Customer customer){
        return repository.save(customer);
    }
    public Customer getCustomerByEmailId(String email){
        return repository.findByEmailId(email);
    }

    public Customer getCustomerByEmailIdAndUserPassword(String email, String password){
        return repository.findByEmailIdAndPassword(email,password);
    }
}
