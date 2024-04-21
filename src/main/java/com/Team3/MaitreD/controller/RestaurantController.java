// Controller for Restaurant pages
package com.Team3.MaitreD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Team3.MaitreD.models.RestaurantDTO;
import com.Team3.MaitreD.services.RestaurantService;


@RestController
@RequestMapping("/restaurant")
@CrossOrigin("*")
public class RestaurantController {
	
	@Autowired
	RestaurantService restaurantService;

	@PostMapping("/registration/{username}")
	public void registerRestaurant(@RequestBody RestaurantDTO body, @PathVariable String username) {
		
		username = username.replace("\"", "");
		System.out.println(username);
		restaurantService.registerRestaurant(username, body.getRestaurantName(), body.getAddress(), 
				body.getPhoneNumber(), body.getCuisine(), body.getOpeningTime(), body.getClosingTime());
		
	}

}
