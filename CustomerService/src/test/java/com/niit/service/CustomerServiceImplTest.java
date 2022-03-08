package com.niit.service;

import com.niit.exception.CustomerAlreadyExistsException;
import com.niit.model.Customer;
import com.niit.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    private Customer customer;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;


    @BeforeEach
    void setUp() {
        customer = new Customer("test@gmail.com","test",9999958690l,"test.jpg",1,"test");
    }

    @AfterEach
    void tearDown() {
        customer = null;
    }
    @Test
    public void customerIsSavedReturnSuccess() throws CustomerAlreadyExistsException {
        when(customerRepository.findById(customer.getEmailId())).thenReturn(Optional.empty());
        when(customerRepository.save(any())).thenReturn(customer);
        assertEquals(customer,customerService.saveCustomer(customer));
        verify(customerRepository,times(1)).save(any());
        verify(customerRepository,times(1)).findById(any());

    }
    @Test
    public void customerIsSavedReturnFailure() {
        when(customerRepository.findById(customer.getEmailId())).thenReturn(Optional.of(customer));
        //when findById is called it returns a customer already present in repository hence Exception is thrown.
        assertThrows(CustomerAlreadyExistsException.class,()->customerService.saveCustomer(customer));
        verify(customerRepository,times(0)).save(any());
        verify(customerRepository,times(1)).findById(any());
    }

}