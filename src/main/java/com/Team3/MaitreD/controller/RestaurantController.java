package com.Team3.MaitreD.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/restaurant")
@CrossOrigin("*")
public class RestaurantController {
	@GetMapping("/home")
	public String restaurantView() {
		return "RestaurantHome";
	}

	@GetMapping("/profile")
	public String restaurantProfile() {
		return "restaurantprofile";
	}
}
