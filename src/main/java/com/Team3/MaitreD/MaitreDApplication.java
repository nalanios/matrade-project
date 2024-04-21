//Main class, runs application

package com.Team3.MaitreD;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.Team3.MaitreD.models.Role;
import com.Team3.MaitreD.repository.RoleRepository;
import com.Team3.MaitreD.repository.UserRepository;

@SpringBootApplication
public class MaitreDApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaitreDApplication.class, args);
	}
	//Creates three roles when booted unless already created
	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncode){
		return args ->{
			if(roleRepository.findByAuthority("CUSTOMER").isPresent()) return;
			roleRepository.save(new Role("ADMIN"));
			roleRepository.save(new Role("CUSTOMER"));
			roleRepository.save(new Role("RESTAURANT"));

		};
	
	}

	

}
