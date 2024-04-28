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
			
			if(!restaurantRepository.findByRestaurantName("Marty's Pizza").isPresent()) {
				
				Resource resource1 = resourceLoader.getResource("classpath:static/imgs/marty.jpg");
				InputStream pic1 = resource1.getInputStream();
				byte[] martys = pic1.readAllBytes();
				restaurantRepository.save(new Restaurant("Marty's Pizza", "717 Liberty Ave., Columbia, MD, 21075", "1-805-717-4226", "Italian", "09:00", "21:00", martys));

				Resource resource2 = resourceLoader.getResource("classpath:static/imgs/sandwich.jpg");
				InputStream pic2 = resource2.getInputStream();
				byte[] sandwich = pic2.readAllBytes();
				restaurantRepository.save(new Restaurant("Sandwich Joe", "113 Main Street, Columbia, MD, 21075", "1-805-844-4895", "Sandwich", "09:00", "21:00", sandwich));

				Resource resource3 = resourceLoader.getResource("classpath:static/imgs/bbq.jpg");
				InputStream pic3 = resource3.getInputStream();
				byte[] bbq = pic3.readAllBytes();
				restaurantRepository.save(new Restaurant("Houston BBQ", "29 W. Laurel Avenue, Columbia, MD, 21075", "1-805-944-4895", "American", "09:00", "21:00", bbq));

				Resource resource4 = resourceLoader.getResource("classpath:static/imgs/china.jpg");
				InputStream pic4 = resource4.getInputStream();
				byte[] china = pic4.readAllBytes();
				restaurantRepository.save(new Restaurant("Shanghai Kitchen", "12 Something Street, Columbia, MD, 21075", "1-302-844-4895", "Chinese", "09:00", "21:00", china));

				Resource resource5 = resourceLoader.getResource("classpath:static/imgs/french.jpeg");
				InputStream pic5 = resource5.getInputStream();
				byte[] french = pic5.readAllBytes();
				restaurantRepository.save(new Restaurant("Au revoir", "1100 Thomas Ave, Columbia, MD, 21075", "1-304-844-4895", "French", "09:00", "21:00", french));

				Resource resource6 = resourceLoader.getResource("classpath:static/imgs/india.jpg");
				InputStream pic6 = resource6.getInputStream();
				byte[] india = pic6.readAllBytes();
				restaurantRepository.save(new Restaurant("Royal Curry", "944 55th Avenue, Columbia, MD, 21075", "1-544-844-4895", "Indian", "09:00", "21:00", india));

				Resource resource7 = resourceLoader.getResource("classpath:static/imgs/mexican.jpg");
				InputStream pic7 = resource7.getInputStream();
				byte[] mexican = pic7.readAllBytes();
				restaurantRepository.save(new Restaurant("La Chiquita", "518 W. Laurel Avenue, Columbia, MD, 21075", "1-250-844-4895", "Mexican", "09:00", "21:00", mexican));

				Resource resource8 = resourceLoader.getResource("classpath:static/imgs/pasta.jpg");
				InputStream pic8 = resource8.getInputStream();
				byte[] pasta = pic8.readAllBytes();
				restaurantRepository.save(new Restaurant("Trattoria Roma", "12 Northshore Drive, Columbia, MD, 21075", "1-350-844-4895", "Italian", "09:00", "21:00", pasta));

				Resource resource9 = resourceLoader.getResource("classpath:static/imgs/steak.jpg");
				InputStream pic9 = resource9.getInputStream();
				byte[] steak = pic9.readAllBytes();
				restaurantRepository.save(new Restaurant("Empire Steakhouse", "255 Main Street, Columbia, MD, 21075", "1-777-844-4895", "American", "09:00", "21:00", steak));

				Resource resource10 = resourceLoader.getResource("classpath:static/imgs/sushi.jpg");
				InputStream pic10 = resource10.getInputStream();
				byte[] sushi = pic10.readAllBytes();
				restaurantRepository.save(new Restaurant("Ototo", "150 Shibuya Street, Columbia, MD, 21075", "1-888-844-4895", "Japanese", "09:00", "21:00", sushi));
			}
			
			if(!userRepository.findByUsername("Account1").isPresent()) {
				Set<Role> authorities = new HashSet<>();
				Role userType = roleRepository.findByAuthority("RESTAURANT").get();
	        	authorities.add(userType);

	        	userRepository.save(new ApplicationUser(null, "Account1", "Account1@gmail.com", passwordEncode.encode("Password1!"), authorities, 
	        						restaurantRepository.findByRestaurantName("Marty's Pizza").get().getRestaurantID()));

	        	userRepository.save(new ApplicationUser(null, "Account2", "Account2@gmail.com", passwordEncode.encode("Password1!"), authorities, 
						restaurantRepository.findByRestaurantName("Sandwich Joe").get().getRestaurantID()));

	        	userRepository.save(new ApplicationUser(null, "Account3", "Account3@gmail.com", passwordEncode.encode("Password1!"), authorities, 
						restaurantRepository.findByRestaurantName("Houston BBQ").get().getRestaurantID()));

	        	userRepository.save(new ApplicationUser(null, "Account4", "Account4@gmail.com", passwordEncode.encode("Password1!"), authorities, 
						restaurantRepository.findByRestaurantName("Shanghai Kitchen").get().getRestaurantID()));

	        	userRepository.save(new ApplicationUser(null, "Account5", "Account5@gmail.com", passwordEncode.encode("Password1!"), authorities, 
						restaurantRepository.findByRestaurantName("Au revoir").get().getRestaurantID()));

	        	userRepository.save(new ApplicationUser(null, "Account6", "Account6@gmail.com", passwordEncode.encode("Password1!"), authorities, 
						restaurantRepository.findByRestaurantName("Royal Curry").get().getRestaurantID()));

	        	userRepository.save(new ApplicationUser(null, "Account7", "Account7@gmail.com", passwordEncode.encode("Password1!"), authorities, 
						restaurantRepository.findByRestaurantName("La Chiquita").get().getRestaurantID()));

	        	userRepository.save(new ApplicationUser(null, "Account8", "Account8@gmail.com", passwordEncode.encode("Password1!"), authorities, 
						restaurantRepository.findByRestaurantName("Trattoria Roma").get().getRestaurantID()));

	        	userRepository.save(new ApplicationUser(null, "Account9", "Account9@gmail.com", passwordEncode.encode("Password1!"), authorities, 
						restaurantRepository.findByRestaurantName("Empire Steakhouse").get().getRestaurantID()));

	        	userRepository.save(new ApplicationUser(null, "Account10", "Account10@gmail.com", passwordEncode.encode("Password1!"), authorities, 
						restaurantRepository.findByRestaurantName("Ototo").get().getRestaurantID()));
			}



		};
		
	}


}
