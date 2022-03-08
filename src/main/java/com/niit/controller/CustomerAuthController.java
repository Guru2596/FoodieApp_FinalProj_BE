package com.niit.controller;

import com.niit.model.Customer;
import com.niit.service.CustomerAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerAuthController {

    @Autowired
    private CustomerAuthService service;

    @PostMapping("/api/v1/custregister")
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

    @PostMapping("/api/v1/custlogin")
    public Customer userLogin(@RequestBody Customer customer) throws Exception {
        String tempororyEmaiId=customer.getEmailId();
        String tempororyPassword=customer.getPassword();
        Customer obj2=null;
        if(tempororyEmaiId !=null && tempororyPassword !=null){
            obj2=service.getCustomerByEmailIdAndUserPassword(tempororyEmaiId,tempororyPassword);
        }
        if(obj2 == null){
            throw new Exception("field cannot be empty");
        }
        return obj2;
    }

}
