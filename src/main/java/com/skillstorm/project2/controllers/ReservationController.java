package com.skillstorm.project2.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.project2.models.Reservation;
import com.skillstorm.project2.services.ReservationService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
	
	private ReservationService rsvpService;
	
	@Autowired
	public ReservationController(ReservationService rsvpService)
	{
		this.rsvpService = rsvpService;
	}
	
	//get reservation by id
	@GetMapping("/{id}")
	public Reservation getReservationById(@RequestParam long id)
	{
		return rsvpService.getReservationById(id);
	}
	
	
	//get all reservations of a particular user
	@GetMapping("/user/{userId}")
	public Set<Reservation> getAllReservationByUser(@RequestParam long userId)
	{
		return rsvpService.getAllReservations(userId);
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
	public ResponseEntity<Reservation> editReservation(@RequestParam long id, @RequestBody Reservation reservation)
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
	
	//delete reservation by id
	@DeleteMapping("/{id}")
	public void deleteReservation(@RequestParam long id)
	{
		rsvpService.deleteReservation(id);
	}
	

}
