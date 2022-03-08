package com.niit.repository;

import com.niit.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,String> {
   Customer findByEmailIdAndPassword(String emailid, String password);
}
