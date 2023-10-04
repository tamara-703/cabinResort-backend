package com.skillstorm.project2.controllers;

import java.security.Principal;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.project2.models.GuestInformation;
import com.skillstorm.project2.models.Reservation;
import com.skillstorm.project2.services.UserService;

/** 
 * USER'S PROFILE
 * This Controller will only be accessed when a user is logged into their account
 * 
 */
//production url: http://project2-cabin-fever.s3-website-us-east-1.amazonaws.com/
//development url: http://localhost:4200/

@CrossOrigin(origins = "http://project2-cabin-fever.s3-website-us-east-1.amazonaws.com/",  allowedHeaders= "*")
@RestController
@RequestMapping("/user")
public class UserController {

	private UserService usrService;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	public UserController(UserService usrService) {
		this.usrService = usrService;
	}

	/**
	 * 
	 * get the Principal (all metadata of the user object)
	 * */
	@GetMapping
	public Principal getPrincipal(Principal principal)
	{
		return principal;
	}

	/**
	 * 
	 * get the user object (i.e only the user's information)
	 * */
	@GetMapping("/profile")
	public GuestInformation getUser(@AuthenticationPrincipal GuestInformation user)
	{
		return user;
	}
	
	/**
	 * 
	 * Returns updated user profile
	 * 
	 * */
	@GetMapping("/profile/{userId}")
	public GuestInformation getUserById(@PathVariable long userId)
	{
		return usrService.findByUserId(userId);
	}
	

	/**
	 * Persists the updated details of the user profile to the DB
	 * */
	@PutMapping("/profile/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public GuestInformation editUserProfile(@RequestBody GuestInformation gi, @PathVariable long id)
	{
		return usrService.editById(gi,id);
		
		

	}
}
