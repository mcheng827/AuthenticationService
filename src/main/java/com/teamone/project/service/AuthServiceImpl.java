package com.teamone.project.service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.teamone.project.Customer;
import com.teamone.project.Token;

@Repository
public class AuthServiceImpl implements AuthService {
	
	@Override
	public ResponseEntity<?> createTokenForCustomer(@RequestBody Customer customer) {
		String token = getToken(customer);		
		
		if (token != null) {
			ResponseEntity<?> response = ResponseEntity.ok(new Token(token));
			return response;
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
	}

	public String getToken(Customer customer) {
		String name = customer.getName();
		String password = customer.getPassword();
		
		if (name != null && password != null && password.length() > 2) {
			long fiveHoursInMillis = 1000 * 60 * 60 * 5;
		    Date expireDate = new Date(System.currentTimeMillis() + fiveHoursInMillis);
			Algorithm algorithm = Algorithm.HMAC256("secret");
			
			String tokenString = JWT.create()
					.withSubject(customer.getName())
					.withIssuer(customer.getEmail())
					.withClaim("name", correctNameAndPassword(customer.getName(), customer.getPassword())) //***
					.withExpiresAt(expireDate)
					.sign(algorithm);
			
		    tokenString = "Bearer " + tokenString + "comeonin";
		    return tokenString;
		    
		} else {
			return null;
		}
	}
	
	private boolean correctNameAndPassword (String name, String password) {
		
		Customer customer = getCustomerByNameFromCustomerAPI(name);
		if (customer.getName().equals(name)) { // validate name
			if (customer.getPassword().equals(password)) { // if name is valid, validate password
				return true;
			} else {
				System.out.println("Incorrect name and password");
				return false;
			}
		} else {
			System.out.println("Invalid name");
			return false;
		}
		
	}
	
	private Customer getCustomerByNameFromCustomerAPI(String name) {
		Customer customerToGet = new Customer();
		List<LinkedHashMap<String, String>> customers = (new RestTemplate()).getForObject("http://localhost:8023/api/customers", List.class);
		
		for (LinkedHashMap<String, String> customer : customers) {
			System.out.println("This is the customer:" + customer);

			if (customer.containsValue(name)) {
				customerToGet.setName(customer.get("name"));
				customerToGet.setPassword(customer.get("password"));
			}
		}
		return customerToGet;
	}
	
}
