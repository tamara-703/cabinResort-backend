package com.skillstorm.project2.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.project2.models.GuestInformation;
import com.skillstorm.project2.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

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
	@Operation(summary = "Gets user with principal object after log in")
	@ApiResponses(value = {
			@ApiResponse(responseCode="200",
						 description ="User with principal object was retreived",
						 content = {@Content(mediaType="application/json")}),
			@ApiResponse(responseCode="500",
			 description ="Server issue",
			 content = @Content)
	})
	@SecurityRequirement(name = "basic Auth", scopes = "read")
	@GetMapping
	public Principal getPrincipal(Principal principal)
	{
		return principal;
	}

	/**
	 * 
	 * get the user object (i.e only the user's information)
	 * */
	@Operation(summary = "Gets user when they log in")
	@ApiResponses(value = {
			@ApiResponse(responseCode="200",
						 description ="User was logged in and retreived",
						 content = {@Content(mediaType="application/json")}),
			@ApiResponse(responseCode="401",
			 description ="Invalid log in credentials",
			 content = @Content)
	})
	@SecurityRequirement(name = "basic Auth", scopes = "read")
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
	@Operation(summary = "Gets User id")
	@ApiResponses(value = {
			@ApiResponse(responseCode="200",
						 description ="Got user with given id",
						 content = {@Content(mediaType="application/json")}),
			@ApiResponse(responseCode="404",
			 description ="No user with given id found",
			 content = @Content)
	})
	@GetMapping("/profile/{userId}")
	public GuestInformation getUserById(@PathVariable long userId)
	{
		return usrService.findByUserId(userId);
	}
	

	/**
	 * Persists the updated details of the user profile to the DB
	 * */
	@Operation(summary = "Updates User id")
	@ApiResponses(value = {
			@ApiResponse(responseCode="200",
						 description ="Updated user with given id",
						 content = {@Content(mediaType="application/json")}),
			@ApiResponse(responseCode="401",
			 description ="User must be logged in to update account",
			 content = @Content)
	})
	@PutMapping("/profile/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public GuestInformation editUserProfile(@RequestBody GuestInformation gi, @PathVariable long id)
	{
		return usrService.editById(gi,id);
		
		

	}
}
