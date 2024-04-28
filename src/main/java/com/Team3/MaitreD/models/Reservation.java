package com.Team3.MaitreD.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.IncrementGenerator;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="reservations")
public class Reservation {
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator = "seq")
	@GenericGenerator(name = "seq", type=IncrementGenerator.class)
	private Integer reservationID;
	private Integer customerID; // foreign key for customer
    private Integer restaurantID; // foreign key for restaurant
	private Integer partySize;
	private String reservationTime;
	private String reservationDate;
	
    public Reservation() {
		super();
	}

    public Reservation(Integer customerID, Integer restaurantID, Integer partySize, String reservationTime, String reservationDate) {
        super();
        this.customerID = customerID;
        this.restaurantID = restaurantID;
        this.partySize = partySize;
        this.reservationTime = reservationTime;
        this.reservationDate = reservationDate;
    }

    public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public Integer getReservationID() {
		return reservationID;
	}

	public void setReservationID(Integer reservationID) {
		this.reservationID = reservationID;
	}

	public Integer getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}

	public Integer getRestaurantID() {
		return restaurantID;
	}

	public void setRestaurantID(Integer restaurantID) {
		this.restaurantID = restaurantID;
	}

    public Integer getPartySize() {
		return partySize;
	}

	public void setPartySize(Integer partySize) {
		this.partySize = partySize;
	}

    public String getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(String reservationTime) {
		this.reservationTime = reservationTime;
	}
}