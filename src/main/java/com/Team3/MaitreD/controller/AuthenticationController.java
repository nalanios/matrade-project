// Controller for register and login
package com.Team3.MaitreD.controller;

import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Team3.MaitreD.models.ApplicationUser;
import com.Team3.MaitreD.models.LoginResponseDTO;
import com.Team3.MaitreD.models.RegistrationDTO;
import com.Team3.MaitreD.repository.UserRepository;
import com.Team3.MaitreD.services.AuthenticationService;

@RestController
@CrossOrigin("*")
public class AuthenticationController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private AuthenticationService authenticationService;

	@PostMapping("/register")
    public ApplicationUser registerUser(RegistrationDTO body){
        return authenticationService.registerUser(body.getUsername(), body.getEmail(), body.getPassword(), body.getAccountType());
    }
 
    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body){
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }
    // Testing for image upload
    @GetMapping("/restaurant/{username}")
    public String getImage (@PathVariable String username) {
    	username = username.replace("\"", "");
    	Optional<ApplicationUser> user = userRepository.findByUsername(username);
		ApplicationUser currentUser = user.get();
    	byte[] photo = currentUser.getPhoto();
    	String image = Base64.encodeBase64String(photo);
    	return image;
    }
    	
    
}
