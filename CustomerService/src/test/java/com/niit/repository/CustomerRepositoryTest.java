package com.niit.repository;

import com.niit.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class CustomerRepositoryTest {

    private Customer customer;
    @Autowired
    private CustomerRepository customerRepository;
    @BeforeEach
    void setUp() {
        customer = new Customer("test@gmail.com","test",9999958690l,"test.jpg",1,"test");
    }
    @AfterEach
    void tearDown() {
        customer = null;
    }

    @Test
    public void givenCustomerToSaveShouldReturnProduct(){
        customerRepository.save(customer);
        Customer customerobj = customerRepository.findById(customer.getEmailId()).get();
        assertNotNull(customerobj);
        assertEquals(customer.getEmailId(),customerobj.getEmailId());

    }
    @Test
    public void givenEmailAndPasswordShouldReturnCustomer(){
        customerRepository.save(customer);
        Customer customerobj = customerRepository.findByEmailIdAndPassword(customer.getEmailId(),customer.getPassword());
        assertNotNull(customerobj);
        assertEquals(customer.getEmailId(),customerobj.getEmailId());

    }


}