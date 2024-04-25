// Controller for register and login
package com.Team3.MaitreD.controller;

import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Team3.MaitreD.models.ApplicationUser;
import com.Team3.MaitreD.models.LoginResponseDTO;
import com.Team3.MaitreD.models.RegistrationDTO;
import com.Team3.MaitreD.repository.UserRepository;
import com.Team3.MaitreD.services.AuthenticationService;
import com.Team3.MaitreD.services.TokenService;

@RestController
@CrossOrigin("*")
public class AuthenticationController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TokenService tokenService;

	@PostMapping("/register")
    public ApplicationUser registerUser(RegistrationDTO body){
        return authenticationService.registerUser(body.getUsername(), body.getEmail(), body.getPassword(), body.getAccountType());
    }
 
    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body){
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }
    	
    @GetMapping("/check-roles")
    public ResponseEntity<List<String>> getMethodName(@RequestParam String token) {
        List<String> roles = tokenService.getRolesFromToken(token);
        return ResponseEntity.ok(roles);
    }
}
