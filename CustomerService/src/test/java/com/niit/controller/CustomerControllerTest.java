package com.niit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.model.Customer;
import com.niit.service.CustomerServiceImpl;
import com.niit.service.SecurityTokenGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    private Customer customer;
    @Mock
    private CustomerServiceImpl customerService;
    @Mock
    private SecurityTokenGenerator securityTokenGenerator;
    @InjectMocks
    private CustomerController customerController;


    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        customer = new Customer("test@gmail.com","test",9999958690l,"test.jpg",1,"test");
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }
    @AfterEach
    void tearDown() {
        customer = null;
    }

    @Test
    public void customerRegisterToReturnSuccess() throws Exception {
        when(customerService.saveCustomer(any())).thenReturn(customer);
        mockMvc.perform(post("/api/customer/register")//mockCall
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(customer)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
        verify(customerService,times(1)).saveCustomer(any());

    }
    @Test
    public void customerLoginToReturnSuccess() throws Exception {
        when(customerService.getCustomerByEmailIdAndUserPassword(any(),any())).thenReturn(customer);
        mockMvc.perform(post("/api/customer/login")//mockhttpservletrequest
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(customer)))//, convert json to string and than expect.
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
        verify(securityTokenGenerator,times(1)).generateToken(any());

    }


    private static String jsonToString(final Object ob)  {
        String result;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(ob);
            result = jsonContent;
        } catch(JsonProcessingException e) {
            result = "JSON processing error";
        }
        return result;
    }
}