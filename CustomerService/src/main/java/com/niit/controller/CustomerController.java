package com.niit.controller;

import com.niit.model.Customer;
import com.niit.service.CustomerService;
import com.niit.service.SecurityTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;
    @Autowired
    private SecurityTokenGenerator securityTokenGenerator;


    @PostMapping("/register")
    public Customer customerReister(@RequestBody Customer customer) throws Exception {
        String tempororyEmaiId=customer.getEmailId();
        if(tempororyEmaiId !=null && !"".equals(tempororyEmaiId)){
            Customer obj=service.getCustomerByEmailId(tempororyEmaiId);
            if (obj != null){
                throw new Exception("Customer with this "+tempororyEmaiId+" is already in use please chose another EmailId");
            }
        }
        Customer obj1=null;//taking one variable
        obj1=service.saveCustomer(customer);
        return obj1;
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody Customer customer) throws Exception {
        ResponseEntity<?> responseEntity;
        String tempEmail = customer.getEmailId();
        String tempPassword = customer.getPassword();
        Map<String, String> map = null;
        try {
            Customer customer1 = service.getCustomerByEmailIdAndUserPassword(tempEmail,tempPassword);
            if (customer1.getEmailId().equals(customer.getEmailId())) {
                map = securityTokenGenerator.generateToken(customer);
            }
            responseEntity = new ResponseEntity(map, HttpStatus.OK);
        }
        catch (Exception e){
            responseEntity = new ResponseEntity("Try after sometime!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
