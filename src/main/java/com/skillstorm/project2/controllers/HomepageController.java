package com.skillstorm.project2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.project2.exceptions.UserAlreadyExistsException;
import com.skillstorm.project2.models.Cabin;
import com.skillstorm.project2.models.GuestInformation;
import com.skillstorm.project2.services.CabinService;
import com.skillstorm.project2.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/*
 * HOMEPAGE 
 * This controller can be accessed by both users and guests
 */


//@CrossOrigin(origins = "http://localhost:4200/", allowedHeaders= "*")


/**
 * This endpoint returns the homepage of the Cabin Resorts website
 * */
@CrossOrigin(origins = "http://project2-cabin-fever.s3-website-us-east-1.amazonaws.com/", allowedHeaders= "*")
@RestController
@RequestMapping("/homepage")
public class HomepageController {
	
	private CabinService cabinService;
	private UserService userService;
	
	@Autowired
	public HomepageController(CabinService cabinService, UserService userService)
	{
		this.cabinService = cabinService;
		this.userService = userService;
	}
	
	/**
	 * Returns all the list of cabins of a the selected State
	 * */
	//get all cabins by state id
	@Operation(summary = "Gets list of cabins by local id")
	@ApiResponses(value = {
			@ApiResponse(responseCode="200",
						 description ="Got list of cabins by local id",
						 content = {@Content(mediaType="application/json")}),
			@ApiResponse(responseCode="404",
			 description ="No Cabins found for local id",
			 content = @Content)
	})
	@GetMapping("/{stateId}")
	public List<Cabin> getCabinByStateId(@PathVariable String stateId)
	{
		return cabinService.getCabinByStateId(stateId);
	}
	
	/**
	 * Returns the details of the particular cabin that the guest would like to reserve
	 * */
	@Operation(summary = "Gets cabin by cabin id")
	@ApiResponses(value = {
			@ApiResponse(responseCode="200",
						 description ="Got cabin id",
						 content = {@Content(mediaType="application/json")}),
			@ApiResponse(responseCode="401",
			 description ="User must be signed in to get cabin by id",
			 content = @Content),
			@ApiResponse(responseCode="404",
			 description ="No Cabin found for that id",
			 content = @Content)
	})
	@GetMapping("/reserve/{id}")
	public Cabin getCabinById(@PathVariable long id)
	{
		return cabinService.getCabinById(id);
	}
	
	/**
	 * Returns the guest information for the user profile page
	 * */
	@Operation(summary = "Gets a user by thier username")
	@ApiResponses(value = {
			@ApiResponse(responseCode="200",
						 description ="Found User with given username",
						 content = {@Content(mediaType="application/json")}),
			@ApiResponse(responseCode="404",
			 description ="No User found with that username",
			 content = @Content)
	})
	@GetMapping("/newuser/{username}")
	public GuestInformation getUserByUserName(@PathVariable String username)
	{
		return userService.getUserByUsername(username);
	}
	
	/**
	 * Saves the newly created user information to the DB
	 * */
	//user signup
	@Operation(summary = "Gets a new user")
	@ApiResponses(value = {
			@ApiResponse(responseCode="200",
						 description ="New User Created",
						 content = {@Content(mediaType="application/json")}),
			@ApiResponse(responseCode="404",
			 description ="No User found with that username",
			 content = @Content)
	})
	@PostMapping("/signup")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void registerUser(@RequestBody GuestInformation newUser) throws UserAlreadyExistsException
	{
		userService.registerUser(newUser);
	}
	
	
	
	

}
