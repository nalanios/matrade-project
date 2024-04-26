//Main class, runs application

package com.Team3.MaitreD;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.Team3.MaitreD.models.ApplicationUser;
import com.Team3.MaitreD.models.Restaurant;
import com.Team3.MaitreD.models.Role;
import com.Team3.MaitreD.repository.RestaurantRepository;
import com.Team3.MaitreD.repository.RoleRepository;
import com.Team3.MaitreD.repository.UserRepository;

@SpringBootApplication
public class MaitreDApplication {
	@Autowired 
	private ResourceLoader resourceLoader;
	
	public static void main(String[] args) {
		SpringApplication.run(MaitreDApplication.class, args);
	}
	//Creates three roles when booted unless already created
	@Bean
	CommandLineRunner createRoles(RoleRepository roleRepository, UserRepository userRepository, RestaurantRepository restaurantRepository, PasswordEncoder passwordEncode){
		return args ->{
			if(!roleRepository.findByAuthority("CUSTOMER").isPresent()) {
				roleRepository.save(new Role("ADMIN"));
				roleRepository.save(new Role("CUSTOMER"));
				roleRepository.save(new Role("RESTAURANT"));
			}
			
			if(!userRepository.findByUsername("Account1").isPresent()) {
				Set<Role> authorities = new HashSet<>();
				Role userType = roleRepository.findByAuthority("RESTAURANT").get();
	        	authorities.add(userType);
	        	
	        	userRepository.save(new ApplicationUser(null, "Account1", "Account1@gmail.com", passwordEncode.encode("Password1!"), authorities, 1));
			}
			
			if(!restaurantRepository.findById(1).isPresent()) {
				
				Resource resource = resourceLoader.getResource("classpath:static/imgs/marty.jpg");
				InputStream pic1 = resource.getInputStream();
				byte[] martys = pic1.readAllBytes();
				restaurantRepository.save(new Restaurant("Marty's Pizza", "717 Liberty Ave., Columbia, MD, 21075", "1-805-717-4226", "Italian", "09:00", "21:00", martys));
			}

		};
		
	}
	

}
