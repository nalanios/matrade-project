//Controller for handling reservations.  Phase 3 code goal
package com.Team3.MaitreD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.Team3.MaitreD.models.Customer;
import com.Team3.MaitreD.models.Reservation;
import com.Team3.MaitreD.models.ReservationDTO;
import com.Team3.MaitreD.models.Restaurant;
import com.Team3.MaitreD.services.CustomerService;
import com.Team3.MaitreD.services.ReservationService;
import com.Team3.MaitreD.services.RestaurantService;

@RestController
@CrossOrigin("*")
public class ReservationController {

    @Autowired
	private ReservationService reservationService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/reserve")
    public Reservation reserve(@RequestBody ReservationDTO body){
    	return reservationService.createReservation(body.getCustomerID(), body.getRestaurantID(), body.getPartySize(), body.getReservationTime());
    }

    @GetMapping("/reservation/{username}/get-all")
    public List<Reservation> getAllUserReservations (@PathVariable String username) {
        username = username.replace("\"", "");
        Customer customer = customerService.getCurrentCustomer(username);
        Integer customerID = customer.getCustomerID();
    	return reservationService.getAllUserReservations(customerID);
    }

    @GetMapping("/restaurant-reservations/{username}")
    public List<Reservation> getAllReservations (@PathVariable String username) {
        username = username.replace("\"", "");
        Restaurant restaurant = restaurantService.getCurrentRestaurant(username);
        Integer restaurantID = restaurant.getRestaurantID();
    	return reservationService.getAllRestaurantReservations(restaurantID);
    }
}