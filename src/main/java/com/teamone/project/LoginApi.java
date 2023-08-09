package com.teamone.project;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.teamone.project.service.AuthServiceImpl;

@RestController
@CrossOrigin
@Transactional
@RequestMapping("/account")
public class LoginApi {

	@Autowired
	AuthServiceImpl service;
	
	@PostMapping("/token")
	public ResponseEntity<?> createTokenForCustomer(@RequestBody Customer customer) {
		return service.createTokenForCustomer(customer);
		
	}
	
}
