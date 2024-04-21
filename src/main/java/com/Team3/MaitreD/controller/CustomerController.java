// Controller for Customer related pages
package com.Team3.MaitreD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Team3.MaitreD.models.CustomerDTO;
import com.Team3.MaitreD.services.CustomerService;



@RestController
@RequestMapping("/customer")
@CrossOrigin("*")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@PostMapping("/registration/{username}")
	public void registerRestaurant(@RequestBody CustomerDTO body, @PathVariable String username) {
		
		username = username.replace("\"", "");
	
		customerService.registerCustomer(username, body.getFirstName(), body.getLastName(), 
				body.getPhoneNumber());
		
	}
	
}
