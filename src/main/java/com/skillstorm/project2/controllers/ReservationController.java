package com.skillstorm.project2.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.project2.models.Reservation;
import com.skillstorm.project2.services.ReservationService;

/** 
 * USER'S RESERVATIONS
 * This Controller can only be accessed when a user is logged into their account
 * */


@CrossOrigin(origins = "http://project2-cabin-fever.s3-website-us-east-1.amazonaws.com/",  allowedHeaders= "*")
@RestController
@RequestMapping("/user/reservations")
public class ReservationController {
	
	private ReservationService rsvpService;
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	public ReservationController(ReservationService rsvpService)
	{
		this.rsvpService = rsvpService;
	}
	
	
	/**
	 * Returns the Reservation details based on the reservation number
	 * */
	@GetMapping("/reservation/{rsvpId}")
	public Reservation getReservationById(@PathVariable long rsvpId)
	{
		return rsvpService.getReservationById(rsvpId);
	}
	
	
	/**
	 * Returns all the reservations made by a particular guest 
	 * */
	@GetMapping("/{userName}")
	public List<Reservation> getAllReservationsByUserName(@PathVariable String userName)
	{
		return rsvpService.getAllReservations(userName);
	}

	
	/**
	 * Creates a new reservation and persists the information in the DB
	 * */
	@PostMapping
	@ResponseStatus(code=HttpStatus.CREATED)
	public void createNewReservation(@RequestBody Reservation reservation)
	{
		
		rsvpService.createNewReservation(reservation);
		logger.debug("Reservation", reservation);
	}

	/**
	 * updates the existing reservation details in the DB
	 * */
	@PutMapping("/{id}")
	public ResponseEntity<Reservation> editReservation(@PathVariable long id, @RequestBody Reservation reservation)
	{
		boolean result = rsvpService.editReservation(reservation, id);
		
		if(result == true)
		{
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
		else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	/**
	 * Deletes an existing reservation based on the reservation number
	 * */
	@DeleteMapping("/{rsvpId}")
	public void deleteReservation(@PathVariable long rsvpId)
	{
		rsvpService.deleteReservation(rsvpId);
	}
	

}
