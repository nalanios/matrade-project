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

	    public ApplicationUser registerUser(String username, String email, String password, String accountType){
	    	
	        String encodedPassword = passwordEncoder.encode(password);
	        Set<Role> authorities = new HashSet<>();
	      
	        Integer accountId = 0;
	        if(accountType.equals("Customer")) {
	        	Role userType = roleRepository.findByAuthority("CUSTOMER").get();
	        	authorities.add(userType);
	        	Customer customer = new Customer();
	        	customerRepository.save(customer);
	        	accountId = customer.getCustomer_id();
	        }else if (accountType.equals("Restaurant")) {
	        	Role userType = roleRepository.findByAuthority("RESTAURANT").get();
	        	authorities.add(userType);
	        	Restaurant restaurant = new Restaurant();
	        	restaurantRepository.save(restaurant);
	        	accountId = restaurant.getRestaurant_id();
	        }
	        
	        
	        return userRepository.save(new ApplicationUser(0, username, email, encodedPassword, accountType, authorities, accountId));
	    }

	    public LoginResponseDTO loginUser(String username, String password){
	    	
	        try{
	            Authentication auth = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(username, password)
	            );
	            
	            String token = tokenService.generateJwt(auth);

	            return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);

	        } catch(AuthenticationException e){
	            return new LoginResponseDTO(null, "");
	        }
	    }

}
