// Save and retrive Customers to Customer table of database
package com.Team3.MaitreD.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Team3.MaitreD.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	Optional<Customer> findById(Integer id);
}
