package com.teamone.project.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.teamone.project.Customer;

public interface AuthService {

	public ResponseEntity<?> createTokenForCustomer(Customer customer);
	
}
