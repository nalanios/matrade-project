// Service to submit new restaurant data to database
package com.Team3.MaitreD.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Team3.MaitreD.models.ApplicationUser;
import com.Team3.MaitreD.models.Restaurant;
import com.Team3.MaitreD.repository.RestaurantRepository;
import com.Team3.MaitreD.repository.UserRepository;
@Service
public class RestaurantService {

	@Autowired
    private UserRepository userRepository;
 	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	/*public void registerRestaurant(String currentUsername, String restaurantName, String address, String phoneNumber, String cuisine, 
								   String openingTime, String closingTime) {
		Restaurant currentRestaurant = getCurrentRestaurant(currentUsername);
		
		currentRestaurant.setRestaurantName(restaurantName);
		currentRestaurant.setAddress(address);
		currentRestaurant.setPhoneNumber(phoneNumber);
		currentRestaurant.setCuisine(cuisine);
		currentRestaurant.setOpeningTime(openingTime);
		currentRestaurant.setClosingTime(closingTime);
		restaurantRepository.save(currentRestaurant);
	}*/
	
	public Restaurant getCurrentRestaurant(String username) {
		Optional<ApplicationUser> user = userRepository.findByUsername(username);
		ApplicationUser currentUser = user.get();
		Integer restaurant_id = currentUser.getAccount_id();
		Optional<Restaurant> restaurant = restaurantRepository.findById(restaurant_id);
		Restaurant currentRestaurant = restaurant.get();
		
		return currentRestaurant;
	}

	//TODO: check restaurant/customer inital object creation logic, could refactor later
	public Restaurant updateRestaurant(String currentUsername, String restaurantName, String address, String phoneNumber, String cuisine, 
									String openingTime, String closingTime){
		if (checkIfRestaurantExistsByUsername(currentUsername)) {
			Restaurant currentRestaurant = getCurrentRestaurant(currentUsername);
			currentRestaurant.setRestaurantName(restaurantName);
			currentRestaurant.setAddress(address);
			currentRestaurant.setPhoneNumber(phoneNumber);
			currentRestaurant.setCuisine(cuisine);
			currentRestaurant.setOpeningTime(openingTime);
			currentRestaurant.setClosingTime(closingTime);
			return restaurantRepository.save(currentRestaurant);
		} else {
			Restaurant restaurant = new Restaurant();
			restaurant.setRestaurantName(restaurantName);
			restaurant.setAddress(address);
			restaurant.setPhoneNumber(phoneNumber);
			restaurant.setCuisine(cuisine);
			restaurant.setOpeningTime(openingTime);
			restaurant.setClosingTime(closingTime);
			return restaurantRepository.save(restaurant);
		}
	}

	public boolean checkIfRestaurantExistsByUsername(String username) {
		//TODO repeated 3 lines, can make into seperate function later
		//TODO check when customer object should be created - refactor this
		// instead of checking if the object exists, checks if the name is null
		// because of the restaurant object creation at initial registration.
		Optional<ApplicationUser> user = userRepository.findByUsername(username);
		ApplicationUser currentUser = user.get();
		Integer account_id = currentUser.getAccount_id();
		Optional<Restaurant> restaurant = restaurantRepository.findById(account_id);
		System.out.println(restaurant.get().getRestaurantName());
		System.out.println(restaurant.get().getRestaurantName() != null);
		return restaurant.get().getRestaurantName() != null;
	}
}