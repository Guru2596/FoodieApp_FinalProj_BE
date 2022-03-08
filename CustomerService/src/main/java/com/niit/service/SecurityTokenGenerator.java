package com.niit.service;

import com.niit.model.Customer;

import java.util.Map;

public interface SecurityTokenGenerator {
	Map<String,String> generateToken(Customer customer);
}
