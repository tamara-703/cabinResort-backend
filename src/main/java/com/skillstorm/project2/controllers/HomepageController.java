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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.project2.exceptions.UserAlreadyExistsException;
import com.skillstorm.project2.models.Cabin;
import com.skillstorm.project2.models.GuestInformation;
import com.skillstorm.project2.services.CabinService;
import com.skillstorm.project2.services.UserService;

/*
 * HOMEPAGE 
 * This controller can be accessed by both users and guests
 */


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
	
	//Test mapping, 
	//delete later
	@GetMapping("/test")
	public String sayHello()
	{
		//test return
		return "<h1>Hello from AWS!</h1>";
	}

	/**
	 * Returns all the list of cabins of a the selected State
	 * */
	//get all cabins by state id
	@GetMapping("/{stateId}")
	public List<Cabin> getCabinByStateId(@PathVariable String stateId)
	{
		return cabinService.getCabinByStateId(stateId);
	}
	
	/**
	 * Returns the details of the particular cabin that the guest would like to reserve
	 * */
	@GetMapping("/reserve/{id}")
	public Cabin getCabinById(@PathVariable long id)
	{
		return cabinService.getCabinById(id);
	}
	
	/**
	 * Returns the guest information for the user profile page
	 * */
	@GetMapping("/newuser/{username}")
	public GuestInformation getUserByUserName(@PathVariable String username)
	{
		return userService.getUserByUsername(username);
	}
	
	/**
	 * Saves the newly created user information to the DB
	 * */
	//user signup
	@PostMapping("/signup")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void registerUser(@RequestBody GuestInformation newUser) throws UserAlreadyExistsException
	{
		userService.registerUser(newUser);
	}
	
	
	
	

}
