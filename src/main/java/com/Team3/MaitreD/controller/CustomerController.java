package com.Team3.MaitreD.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Team3.MaitreD.models.Customer;

@Controller
@RequestMapping("/customer")
@CrossOrigin("*")
public class CustomerController {
	@GetMapping("/home")
	public String customerView() {
	//	@RequestHeader (name="Authorization") String token
		return "CustomerHome";
	}
	
	@GetMapping("/profile")
	public String customerProfile(Customer customer) {
		return "customerprofile";
	}
}
