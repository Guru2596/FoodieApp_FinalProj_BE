package com.niit.service;

import com.niit.exception.CustomerAlreadyExistsException;
import com.niit.exception.InvalidCredentialsException;
import com.niit.model.Customer;
import com.niit.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer) throws CustomerAlreadyExistsException {
        if(customerRepository.findById(customer.getEmailId()).isPresent())
        {
            throw new CustomerAlreadyExistsException();
        }
        return customerRepository.save(customer);
    }

    public Customer getCustomerByEmailIdAndUserPassword(String email, String password) throws InvalidCredentialsException {
        System.out.println("email"+email);
        System.out.println("password"+password);
        Customer loggedInUser = customerRepository.findByEmailIdAndPassword(email,password);
        System.out.println(loggedInUser);
        if(loggedInUser == null)
        {
            throw new InvalidCredentialsException();
        }
        return loggedInUser;
    }
}
