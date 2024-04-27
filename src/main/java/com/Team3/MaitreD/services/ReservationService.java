package com.Team3.MaitreD.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Team3.MaitreD.models.Reservation;
import com.Team3.MaitreD.repository.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;

    public Reservation createReservation(Integer customerID, Integer restaurantID, Integer partySize, String reservationTime){
        Reservation reservation = new Reservation(customerID, restaurantID, partySize, reservationTime);
        return reservationRepository.save(reservation);
    }
    
    public List<Reservation> getAllUserReservations(Integer customerID) {
        return reservationRepository.findByCustomerID(customerID);
    }
}