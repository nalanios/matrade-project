// Save and retrive Restaurants from restaurants table of database
package com.Team3.MaitreD.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Team3.MaitreD.models.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>{
	Optional<Restaurant> findById(Integer id);
	Optional<Restaurant> findByRestaurantName(String restaurantName);
	@Query("SELECT i FROM Restaurant i WHERE LOWER(i.restaurantName) LIKE LOWER(CONCAT('%', :search, '%'))")
	List<Restaurant> findAllByRestaurantNameContaining(@Param("search") String search);
}
