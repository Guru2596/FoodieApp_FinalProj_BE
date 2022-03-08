package com.niit.repository;

import com.niit.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface
CustomerAuthRepository extends JpaRepository<Customer,Integer> {
   public Customer findByEmailId(String emailid);

   public Customer findByEmailIdAndPassword(String emailid, String password);
}
