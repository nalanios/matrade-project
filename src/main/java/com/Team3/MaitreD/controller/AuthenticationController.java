package com.Team3.MaitreD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Team3.MaitreD.models.ApplicationUser;
import com.Team3.MaitreD.models.LoginResponseDTO;
import com.Team3.MaitreD.models.RegistrationDTO;
import com.Team3.MaitreD.services.AuthenticationService;
import com.Team3.MaitreD.services.UserService;

@RestController
//@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {
	//private final UserService userService;
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
}
