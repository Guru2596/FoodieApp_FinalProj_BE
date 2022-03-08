package com.niit.controller;

import com.niit.model.Customer;
import com.niit.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class CustomerControllerTest {

    private Customer customer;
    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }
}