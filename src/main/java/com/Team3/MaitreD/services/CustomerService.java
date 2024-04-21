// Service to submit new customer data to database
package com.Team3.MaitreD.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Team3.MaitreD.models.ApplicationUser;
import com.Team3.MaitreD.models.Customer;
import com.Team3.MaitreD.repository.CustomerRepository;
import com.Team3.MaitreD.repository.UserRepository;
@Service
public class CustomerService {
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public void registerCustomer(String currentUsername, String firstName, String lastName, String phoneNumber) {
		Optional<ApplicationUser> user = userRepository.findByUsername(currentUsername);
		ApplicationUser currentUser = user.get();
		Integer customer_id = currentUser.getAccount_id();
		Optional<Customer> customer = customerRepository.findById(customer_id);
		Customer currentCustomer = customer.get();
		
		currentCustomer.setFirstName(firstName);
		currentCustomer.setLastName(lastName);
		currentCustomer.setPhoneNumber(phoneNumber);
		customerRepository.save(currentCustomer);
	}
}
