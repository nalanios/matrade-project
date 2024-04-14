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

import com.Team3.MaitreD.models.LoginResponseDTO;
import com.Team3.MaitreD.models.Role;

import com.Team3.MaitreD.repository.RoleRepository;
import com.Team3.MaitreD.repository.UserRepository;

@Service
@Transactional
public class AuthenticationService {
	 @Autowired
	    private UserRepository userRepository;
	 
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
	       // Role userRole = roleRepository.findByAuthority("USER").get();
	        
	        Set<Role> authorities = new HashSet<>();
	        //authorities.add(userRole);
	     
	        if(accountType.equals("Customer")) {
	        	System.out.println(accountType);
	        	Role userType = roleRepository.findByAuthority("CUSTOMER").get();
	        	authorities.add(userType);
	        }else if (accountType.equals("Restaurant")) {
	        	Role userType = roleRepository.findByAuthority("RESTAURANT").get();
	        	authorities.add(userType);
	        }
	        
	        
	        return userRepository.save(new ApplicationUser(0, username, email, encodedPassword, accountType, authorities));
	    }
	    
//	    public Customer registerCustomer(String username, String email, String password, String accountType){
//
//	        String encodedPassword = passwordEncoder.encode(password);
//	        Role customerRole = roleRepository.findByAuthority("CUSTOMER").get();
//
//	        Set<Role> authorities = new HashSet<>();
//
//	        authorities.add(customerRole);
//
//	        return customerRepository.save(new Customer(0, username, email, encodedPassword, authorities));
//	    }
	    

	    public LoginResponseDTO loginUser(String username, String password){
	    	System.out.println("login user");
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
