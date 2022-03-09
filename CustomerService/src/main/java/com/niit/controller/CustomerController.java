package com.niit.controller;

import com.niit.exception.CustomerAlreadyExistsException;
import com.niit.exception.InvalidCredentialsException;
import com.niit.model.Customer;
import com.niit.service.CustomerService;
import com.niit.service.SecurityTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;
    @Autowired
    private SecurityTokenGenerator securityTokenGenerator;


    @PostMapping("/register")
    public ResponseEntity<?>  customerRegister(@RequestBody Customer customer)  {
        try{
            return new ResponseEntity<>(service.saveCustomer(customer),HttpStatus.CREATED);
        } catch (CustomerAlreadyExistsException customerAlreadyExistsException){
            return new ResponseEntity<>("Email id is already registered",HttpStatus.CONFLICT);
        }catch(Exception e){
            return new ResponseEntity<>(e.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> loginCustomer(@RequestBody Customer customer)  {
        try{
            Customer retrievedCustomer = service.getCustomerByEmailIdAndUserPassword(customer.getEmailId(),customer.getPassword());
            if(retrievedCustomer==null)
            {
                throw new InvalidCredentialsException();
            }
        }catch (InvalidCredentialsException invalidCredentialsException){
            return new ResponseEntity<>("Invalid Credentials",HttpStatus.UNAUTHORIZED);
        }catch(Exception e){
            return new ResponseEntity<>(e.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Map<String,String> map = securityTokenGenerator.generateToken(customer);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

}
