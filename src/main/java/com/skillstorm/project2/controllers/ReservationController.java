package com.skillstorm.project2.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.project2.models.Reservation;
import com.skillstorm.project2.services.ReservationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.media.Content;

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
	@Operation(summary = "Gets reservation by reservation id")
	@ApiResponses(value = {
			@ApiResponse(responseCode="200",
						 description ="Reservation with id Found",
						 content = {@Content(mediaType="application/json")}),
			@ApiResponse(responseCode="401",
			 description ="Unauthorized to retreive reservation",
			 content = @Content)
	})
	@GetMapping("/reservation/{rsvpId}")
	public Reservation getReservationById(@PathVariable long rsvpId)
	{
		return rsvpService.getReservationById(rsvpId);
	}
	
	
	/**
	 * Returns all the reservations made by a particular guest 
	 * */
	@Operation(summary = "Gets reservation for guest by their username")
	@ApiResponses(value = {
			@ApiResponse(responseCode="200",
						 description ="Reservations from username Found",
						 content = {@Content(mediaType="application/json")}),
			@ApiResponse(responseCode="401",
			 description ="Unauthorized to retreive reservation",
			 content = @Content)
	})
	@GetMapping("/{userName}")
	public List<Reservation> getAllReservationsByUserName(@PathVariable String userName)
	{
		return rsvpService.getAllReservations(userName);
	}

	
	/**
	 * Creates a new reservation and persists the information in the DB
	 * */
	@Operation(summary = "Creates a new reservation")
	@ApiResponses(value = {
			@ApiResponse(responseCode="200",
						 description ="Creates a reservation",
						 content = @Content),
			@ApiResponse(responseCode="401",
			 description ="User not logged in to make reservation",
			 content = @Content)
	})
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
	@Operation(summary = "Updates reservation for reservaion with id")
	@ApiResponses(value = {
			@ApiResponse(responseCode="200",
						 description ="Creates a reservation for user by id",
						 content = @Content),
			@ApiResponse(responseCode="401",
			 description ="User not logged in to make reservation",
			 content = @Content)
	})
	@SecurityRequirement(name = "basic Auth", scopes = "write")
	@PutMapping("/{id}")
	public Reservation editReservation(@PathVariable long id, @RequestBody Reservation reservation)
	{
		
		return rsvpService.editReservation(reservation, id);
		
		
	}
	
	/**
	 * Deletes an existing reservation based on the reservation number
	 * */
	@Operation(summary = "Deletes reservation by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode="200",
						 description ="Deletes reservation with given id",
						 content = {@Content(mediaType="application/json")}),
			@ApiResponse(responseCode="401",
			 description ="User not logged in to delete reservation",
			 content = @Content)
	})
	@DeleteMapping("/{rsvpId}")
	public void deleteReservation(@PathVariable long rsvpId)
	{
		rsvpService.deleteReservation(rsvpId);
	}
	

}
