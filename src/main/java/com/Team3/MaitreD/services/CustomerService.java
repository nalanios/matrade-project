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
	/* 
	public void registerCustomer(String currentUsername, String firstName, String lastName, String phoneNumber) {
		Customer currentCustomer = getCurrentCustomer(currentUsername);
		
		currentCustomer.setFirstName(firstName);
		currentCustomer.setLastName(lastName);
		currentCustomer.setPhoneNumber(phoneNumber);
		customerRepository.save(currentCustomer);
	} */
	
	public Customer updateCustomer(String username, String firstName, String lastName, String phoneNumber){
		Optional<ApplicationUser> user = userRepository.findByUsername(username);
		ApplicationUser currentUser = user.get();
		Integer customer_id = currentUser.getAccount_id();
	    Optional<Customer> existingCustomer = customerRepository.findById(customer_id);
		if (existingCustomer.isPresent()) {
			Customer updatedCustomer = existingCustomer.get();
			updatedCustomer.setFirstName(firstName);
			updatedCustomer.setLastName(lastName);
			updatedCustomer.setPhoneNumber(phoneNumber);
			return customerRepository.save(updatedCustomer);
		} else {
			Customer customer = new Customer();
			customer.setFirstName(firstName);
			customer.setLastName(lastName);
			customer.setPhoneNumber(phoneNumber);
			return customerRepository.save(customer);
		}
	}

	public Customer getCurrentCustomer(String username) {
		Optional<ApplicationUser> user = userRepository.findByUsername(username);
		ApplicationUser currentUser = user.get();
		Integer customer_id = currentUser.getAccount_id();
		Optional<Customer> customer = customerRepository.findById(customer_id);
		Customer currentCustomer = customer.get();
		
		return currentCustomer;
	}

	public boolean checkIfCustomerExistsByUsername(String username) {
		//TODO repeated 3 lines, can make into seperate function later
		//TODO check when customer object should be created - refactor this
		Optional<ApplicationUser> user = userRepository.findByUsername(username);
		ApplicationUser currentUser = user.get();
		Integer customer_id = currentUser.getAccount_id();
		Optional<Customer> customer = customerRepository.findById(customer_id);
		System.out.println(customer.get().getFirstName());
		return customer.get().getFirstName() != null;
	}
}