//Controller for handling reservations.  Phase 3 code goal
package com.Team3.MaitreD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.Team3.MaitreD.models.Reservation;
import com.Team3.MaitreD.models.ReservationDTO;
import com.Team3.MaitreD.services.ReservationService;

@RestController
@CrossOrigin("*")
public class ReservationController {

    @Autowired
	private ReservationService reservationService;

    @PostMapping("/reserve")
    public Reservation reserve(@RequestBody ReservationDTO body){
    	return reservationService.createReservation(body.getCustomerID(), body.getRestaurantID(), body.getPartySize(), body.getReservationTime());
    }
}