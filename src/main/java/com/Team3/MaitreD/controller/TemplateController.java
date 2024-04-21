// Controller for displaying html files in 'templates' folder with Thymeleaf
package com.Team3.MaitreD.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin("*")
public class TemplateController {
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	   
	@GetMapping("/login")
		public String login() {
			return "login";
		}
	    
	@GetMapping("/register")
		public String register() {
			return "register";
		}
	@GetMapping("/restaurant/profile")
	public String restaurantView() {
		return "restaurantprofile";
	}
	
	@GetMapping("/restaurant/upload-photos")
	public String uploadPhotoView() {
		return "uploadForm";
	}
	
	@GetMapping("/restaurant/registration")
	public String registerRestaurant() {
		return "restaurantinformation";
	}
	
	@GetMapping("/customer/home")
	public String customerView() {
		return "CustomerHome";
	}
	
	@GetMapping("/customer/profile")
	public String customerProfile() {
		return "customerprofile";
	}
	
	@GetMapping("/customer/registration")
	public String registerCustomer() {
		return "customerinformation";
	}
}
