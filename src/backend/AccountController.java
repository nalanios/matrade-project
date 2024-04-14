package com.maitred.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AccountController {

    @PostMapping("/register/customer")
    public String registerCustomer(@RequestBody Customer customer) {
        // Handle registration
        return "Customer account created";
    }

    @PostMapping("/register/restaurant")
    public String registerRestaurant(@RequestBody Restaurant restaurant) {
        // Handle registration
        return "Restaurant account created";
    }

    // Add other endpoints as needed
}
