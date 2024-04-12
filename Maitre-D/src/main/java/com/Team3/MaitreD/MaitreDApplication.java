package com.Team3.MaitreD;


import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.Team3.MaitreD.models.ApplicationUser;
import com.Team3.MaitreD.models.Role;
import com.Team3.MaitreD.repository.RoleRepository;
import com.Team3.MaitreD.repository.UserRepository;

@SpringBootApplication
public class MaitreDApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaitreDApplication.class, args);
	}
	
	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncode){
		return args ->{
			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
			Role adminRole = roleRepository.save(new Role("ADMIN"));
			Role userRole = roleRepository.save(new Role("USER"));
			Role customerRole = roleRepository.save(new Role("CUSTOMER"));
			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			ApplicationUser admin = new ApplicationUser(1, "admin", "aaronmitchellemail", passwordEncode.encode("password"), roles);

			userRepository.save(admin);
		};
	
	}

	

}
