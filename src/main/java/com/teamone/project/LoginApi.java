package com.teamone.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamone.project.service.AuthService;

@RestController
@CrossOrigin
@Transactional
@RequestMapping("/account")
public class LoginApi {

	@Autowired
	AuthService service;
	
	@PostMapping("/token")
	public ResponseEntity<?> createTokenForCustomer(@RequestBody Customer customer) {
		return service.createTokenForCustomer(customer);
		
	}
	
}
