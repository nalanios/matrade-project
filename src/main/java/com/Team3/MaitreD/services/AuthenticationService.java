//Service class to register new users, customers, and restaurants, and authenticate login attempts 
package com.Team3.MaitreD.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.Team3.MaitreD.models.ApplicationUser;
import com.Team3.MaitreD.models.Customer;
import com.Team3.MaitreD.models.LoginResponseDTO;
import com.Team3.MaitreD.models.Restaurant;
import com.Team3.MaitreD.models.Role;
import com.Team3.MaitreD.repository.CustomerRepository;
import com.Team3.MaitreD.repository.RestaurantRepository;
import com.Team3.MaitreD.repository.RoleRepository;
import com.Team3.MaitreD.repository.UserRepository;

@Service
@Transactional
public class AuthenticationService {
		@Autowired
	    private UserRepository userRepository;
	 	
		@Autowired
		private RestaurantRepository restaurantRepository;
		
		@Autowired
		private CustomerRepository customerRepository;
		
	    @Autowired
	    private RoleRepository roleRepository;

	    @Autowired
	    private PasswordEncoder passwordEncoder;

	    @Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private TokenService tokenService;
	    //Method that registers new users
	    public ApplicationUser registerUser(String username, String email, String password, String accountType){
	    	
	        String encodedPassword = passwordEncoder.encode(password); //encode password
	        Set<Role> authorities = new HashSet<>(); //HashSet to store users roles/account type (Customer, Restaurant, Admin)
	        Integer accountId = 0; //Variable to hold ID from either restaurant or customer table, depending on user choice
	        
	        if(accountType.equals("Customer")) {
	        	//Get Customer role and add to HashSet
	        	Role userType = roleRepository.findByAuthority("CUSTOMER").get(); 
	        	authorities.add(userType);
	        	//Create new Customer, insert in database, and connect with User
	        	Customer customer = new Customer();
	        	customerRepository.save(customer);
	        	accountId = customer.getCustomerID();
	        }else if (accountType.equals("Restaurant")) {
	        	//Get Restaurant role and add to HashSet
	        	Role userType = roleRepository.findByAuthority("RESTAURANT").get();
	        	authorities.add(userType);
	        	//Create new Restaurant, insert in database, and connect with User
	        	Restaurant restaurant = new Restaurant();
	        	restaurantRepository.save(restaurant);
	        	accountId = restaurant.getRestaurantID();
	        }
	        
	        //Save new User in users table
	        return userRepository.save(new ApplicationUser(null, username, email, encodedPassword, authorities, accountId));
	    }

	    public LoginResponseDTO loginUser(String username, String password){
	    	//Authenticate provided username and password
	        try{
	            Authentication auth = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(username, password)
	            );
	            //Generate JWT
	            String token = tokenService.generateJwt(auth);
	            //Return ApplicationUser and JWT
	            return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);

	        } catch(AuthenticationException e){
	            return new LoginResponseDTO(null, "");
	        }
	    }

}
