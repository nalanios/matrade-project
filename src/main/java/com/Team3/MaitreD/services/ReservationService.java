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

    public Reservation createReservation(Integer customerID, Integer restaurantID, Integer partySize, String reservationTime, String reservationDate){
        Reservation reservation = new Reservation(customerID, restaurantID, partySize, reservationTime, reservationDate);
        return reservationRepository.save(reservation);
    }
    
    public List<Reservation> getAllUserReservations(Integer customerID) {
        List<Reservation> reservations = reservationRepository.findByCustomerID(customerID);
        reservations.sort((o1, o2) -> o1.getReservationDate().compareTo(o2.getReservationDate()));
    	return reservations;
    }

    public List<Reservation> getAllRestaurantReservations(Integer restaurantID) {
    	List<Reservation> reservations = reservationRepository.findByRestaurantID(restaurantID);
        reservations.sort((o1, o2) -> o1.getReservationDate().compareTo(o2.getReservationDate()));
        return reservations;
    }
    
    public Reservation getReservationByID(Integer reservationID) {
        return reservationRepository.findById(reservationID).get();
    }

    public Reservation updateReservation(Integer reservationID, Integer partySize, String reservationTime, String reservationDate) {
        Reservation reservation = getReservationByID(reservationID);
        reservation.setPartySize(partySize);
        reservation.setReservationDate(reservationDate);
        reservation.setReservationTime(reservationTime);
        return reservationRepository.save(reservation);
    }

    public void cancelReservation(Integer reservationID) {
        reservationRepository.deleteById(reservationID);
    }
}