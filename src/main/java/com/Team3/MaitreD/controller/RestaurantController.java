// Controller for Restaurant pages
package com.Team3.MaitreD.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Team3.MaitreD.models.Restaurant;
import com.Team3.MaitreD.models.RestaurantDTO;
import com.Team3.MaitreD.services.RestaurantService;


@RestController
@CrossOrigin("*")
public class RestaurantController {
	
	@Autowired
	RestaurantService restaurantService;
	
	@GetMapping("/profile/{username}/information")
	public Restaurant getInformation(@PathVariable String username) {
		username = username.replace("\"", "");
		return restaurantService.getCurrentRestaurant(username);
	}
	
	@GetMapping("/restaurant/{name}/information")
	public Restaurant getRestaurantByName(@PathVariable String name) {
		name = name.replace("\"", "");
		return restaurantService.getRestaurantByName(name);
	}
	
	@GetMapping("/restaurant/{restaurantID}/details")
	public Restaurant getRestaurantByID(@PathVariable String restaurantID) {
		restaurantID = restaurantID.replace("\"", "");
		return restaurantService.getRestaurantByID(Integer.parseInt(restaurantID));
	}
	
	@PostMapping("restaurant/update-information/{username}")
    public Restaurant updateInformation(@RequestBody RestaurantDTO body, @PathVariable String username) {
		username = username.replace("\"", "");
        return restaurantService.updateRestaurant(username, body.getRestaurantName(), body.getAddress(), body.getPhoneNumber(), body.getCuisine(), body.getOpeningTime(), body.getClosingTime());
    }
	
	@GetMapping("restaurant/check-exists")
	@ResponseBody
    public boolean checkIfRestaurantExists(@RequestParam String username) {
		return restaurantService.checkIfRestaurantExistsByUsername(username);
    }
	
	@PostMapping("restaurant/upload-photos")
	public void uploadPhoto(@RequestParam("file") MultipartFile file, 
			RedirectAttributes redirectAttributes) {
		byte[] filecontent = null;
		try {
			InputStream inputStream = file.getInputStream();
			filecontent = inputStream.readAllBytes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 restaurantService.addPicture("photo", filecontent);
		
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");
		
	}
	
    @GetMapping("/get-image/{username}")
    public String getImage (@PathVariable String username) {
    	username = username.replace("\"", "");
    	Restaurant restaurant = restaurantService.getCurrentRestaurant(username);
    	byte[] photo = restaurant.getPhoto();
    	String image = Base64.encodeBase64String(photo);
    	return image;
    }
    
    @GetMapping("/restaurant/get-all")
    public List<Restaurant> getAllRestaurants () {
    	return restaurantService.getAllRestaurants();
    }

	@GetMapping("/restaurant/filter/name/{searchInput}")
	public List<Restaurant> filterRestaurantsByName(@PathVariable String searchInput) {
		searchInput = searchInput.replace("\"", "");
		return restaurantService.filterRestaurantsByName(searchInput);
	}

	@GetMapping("/restaurant/filter/cuisine/{cuisineSelection}")
	public List<Restaurant> filterRestaurantsByCuisine(@PathVariable String cuisineSelection) {
		cuisineSelection = cuisineSelection.replace("\"", "");
		return restaurantService.filterRestaurantsByCuisine(cuisineSelection);
	}

	@GetMapping("/restaurant/filter/name-and-cuisine/{searchInput}/{cuisineSelection}")
	public List<Restaurant> filterRestaurantsByNameAndCuisine(@PathVariable String searchInput, @PathVariable String cuisineSelection) {
		searchInput = searchInput.replace("\"", "");
		cuisineSelection = cuisineSelection.replace("\"", "");
		return restaurantService.filterRestaurantsByNameAndCuisine(searchInput, cuisineSelection);
	}
}
