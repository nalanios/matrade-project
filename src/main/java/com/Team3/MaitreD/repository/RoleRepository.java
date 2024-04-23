// Save and retrive Role from roles table of database
package com.Team3.MaitreD.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Team3.MaitreD.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	Optional<Role> findByAuthority(String authority);
	
}
