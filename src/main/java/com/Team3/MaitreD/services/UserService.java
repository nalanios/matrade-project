// Service to load users. Still in development, may no longer be necessary
package com.Team3.MaitreD.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Team3.MaitreD.models.ApplicationUser;
import com.Team3.MaitreD.repository.UserRepository;


@Service
public class UserService implements UserDetailsService {
	
//	@Autowired
//	private PasswordEncoder encoder;

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user is not valid"));
	}
	// adding pictures method, works but still testing
	public ApplicationUser addPicture(String username, byte[] photo) {
		Optional<ApplicationUser> user = userRepository.findByUsername(username);
		ApplicationUser currentUser = user.get();
		if(user.isPresent()) {
			currentUser.setPhoto(photo);
			return userRepository.save(currentUser);
		}
		return null;
	}

}
