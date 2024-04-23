// Controller for Restaurant pages
package com.Team3.MaitreD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Team3.MaitreD.models.Restaurant;
import com.Team3.MaitreD.models.RestaurantDTO;
import com.Team3.MaitreD.services.RestaurantService;


@RestController
@CrossOrigin("*")
public class RestaurantController {
	
	@Autowired
	RestaurantService restaurantService;

	/*@PostMapping("/restaurant/registration/{username}")
	public void registerRestaurant(@RequestBody RestaurantDTO body, @PathVariable String username) {
		
		username = username.replace("\"", "");
		System.out.println(username);
		restaurantService.registerRestaurant(username, body.getRestaurantName(), body.getAddress(), 
				body.getPhoneNumber(), body.getCuisine(), body.getOpeningTime(), body.getClosingTime());
		
	}*/
	
	@GetMapping("/profile/{username}/information")
	public Restaurant getInformation(@PathVariable String username) {
		username = username.replace("\"", "");
		return restaurantService.getCurrentRestaurant(username);
	}

	@PostMapping("restaurant/update-information/{username}")
    public Restaurant updateInformation(@RequestBody RestaurantDTO body, @PathVariable String username) {
		username = username.replace("\"", "");
        return restaurantService.updateRestaurant(username, body.getRestaurantName(), body.getAddress(), body.getPhoneNumber(), body.getCuisine(), body.getOpeningTime(), body.getClosingTime());
    }
	
	@GetMapping("restaurant/check-exists")
	@ResponseBody
    public boolean checkIfRestaurantExists(@RequestParam String username) {
		System.out.println(restaurantService.checkIfRestaurantExistsByUsername(username));
		return restaurantService.checkIfRestaurantExistsByUsername(username);
    }
}
