package com.skillstorm.project2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

/*
 * HOMEPAGE 
 * This controller can be accessed by both users and guests
 */

@RestController
@RequestMapping("/homepage")
public class AuthenticationController {
	
	//on homepage front page, display a UI of available cabins
	
	private CabinService cabinService;
	private UserService userService;
	
	@Autowired
	public AuthenticationController(CabinService cabinService, UserService userService)
	{
		this.cabinService = cabinService;
		this.userService = userService;
	}
	
	//get all cabins
	@GetMapping
	public List<Cabin> getAllCabins()
	{
		return cabinService.getAllCabins();
	}
	
	//get cabin by id
	@GetMapping("/{id}")
	public Cabin getCabinById(@PathVariable long id)
	{
		return cabinService.getCabinById(id);
	}
	
	@PostMapping("/signup")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void registerUser(@RequestBody GuestInformation newUser) throws UserAlreadyExistsException
	{
		userService.registerUser(newUser);
	}
	
	
	
	

}
