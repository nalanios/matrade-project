package com.Team3.MaitreD;


import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.Team3.MaitreD.models.ApplicationUser;
import com.Team3.MaitreD.models.Role;
import com.Team3.MaitreD.repository.RoleRepository;
import com.Team3.MaitreD.repository.UserRepository;
import com.Team3.MaitreD.utils.RSAKeyProperties;

@SpringBootApplication
public class MaitreDApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaitreDApplication.class, args);
	}
	
	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncode){
		return args ->{
			if(roleRepository.findByAuthority("CUSTOMER").isPresent()) return;
			roleRepository.save(new Role("ADMIN"));
			//roleRepository.save(new Role("USER"));
			roleRepository.save(new Role("CUSTOMER"));
			roleRepository.save(new Role("RESTAURANT"));
//			Set<Role> roles = new HashSet<>();
//			roles.add(adminRole);
			
			//ApplicationUser admin = new ApplicationUser(1, "admin", "aaronmitchellemail", passwordEncode.encode("password"), roles);

			//userRepository.save(admin);
		};
	
	}

	

}
