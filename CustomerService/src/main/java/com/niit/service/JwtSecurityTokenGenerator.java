package com.niit.service;

import com.niit.model.Customer;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtSecurityTokenGenerator implements SecurityTokenGenerator{

	@Override
	public Map<String, String> generateToken(Customer customer) {
		String jsonWebToken = null;
		JwtBuilder jwtBuilder = Jwts.builder();
		jsonWebToken = jwtBuilder.setSubject(customer.getPassword()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secret").compact();
		Map<String, String> tokenMap = new HashMap<String, String>();
		tokenMap.put("token", jsonWebToken);
		tokenMap.put("message", "Customer Successfully Logged In...!");
		return tokenMap;
	}

}
