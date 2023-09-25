package com.skillstorm.project2.controllers;

import java.util.Set;

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

/* 
 * USER'S RESERVATIONS
 * This Controller can only be accessed when a user is logged into their account
 * */

@CrossOrigin(origins = "http://localhost:4200",  allowedHeaders= "*")
@RestController
@RequestMapping("/user/reservations")
public class ReservationController {
	
	private ReservationService rsvpService;
	
	@Autowired
	public ReservationController(ReservationService rsvpService)
	{
		this.rsvpService = rsvpService;
	}
	
	//get reservation by id
	@GetMapping("/reservation/{rsvpId}")
	public Reservation getReservationById(@PathVariable long rsvpId)
	{
		return rsvpService.getReservationById(rsvpId);
	}
	
	
	//get all reservations made by a single user
	@GetMapping("/{userName}")
	public Set<Reservation> getAllReservationsByUserName(@PathVariable String userName)
	{
		return rsvpService.getAllReservations(userName);
	}

	
	//create a new reservation
	@PostMapping
	@ResponseStatus(code=HttpStatus.CREATED)
	public void createNewReservation(@RequestBody Reservation reservation)
	{
		rsvpService.createNewReservation(reservation);
	}

	//edit reservation by id
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
	
	//delete reservation by its id
	@DeleteMapping("/{rsvpId}")
	public void deleteReservation(@PathVariable long rsvpId)
	{
		rsvpService.deleteReservation(rsvpId);
	}
	

}
