// Admin role route controller.  Not currently used
package com.Team3.MaitreD.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
	
	@GetMapping("/")
	public String helloAdminController() {
		return "Admin level access";
	}
}
