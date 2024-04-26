package com.Team3.MaitreD.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Team3.MaitreD.models.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
	Optional<Reservation> findById(Integer id);
}
