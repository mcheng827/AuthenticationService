package com.teamone.project;

import java.net.URI;
import java.net.URISyntaxException;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@CrossOrigin
@Transactional
@RequestMapping("/register")
public class RegisterApi {

	@PostMapping
	public ResponseEntity<?> registerCustomer(@RequestBody Customer newCustomer, UriComponentsBuilder uri) throws URISyntaxException {
		
		if (newCustomer.getName() == null || newCustomer.getPassword() == null ||  newCustomer.getEmail() == null) {
			
			return ResponseEntity.badRequest().build();
			
		} else {
			
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(newCustomer.getId()).toUri();
			
			return postNewCustomerToCustomerAPI(newCustomer);
			
		}
	}
	
	private ResponseEntity<?> postNewCustomerToCustomerAPI(Customer customer) throws URISyntaxException {
	
			String url = "http://localhost:8023/api/customers";
			
			RestTemplate rt = new RestTemplate();
			rt.postForObject(url, customer, Customer.class);
			
			return ResponseEntity.created(new URI(url)).build();			

	}
}
