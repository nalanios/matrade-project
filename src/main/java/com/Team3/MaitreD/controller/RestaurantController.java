package com.Team3.MaitreD.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin("*")
public class RestaurantController {
	@GetMapping("/home")
	public String restaurantView() {
		return "RestaurantHome";
	}
}
