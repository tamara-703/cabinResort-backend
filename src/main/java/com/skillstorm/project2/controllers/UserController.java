package com.skillstorm.project2.controllers;

import java.security.Principal;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.project2.models.GuestInformation;
import com.skillstorm.project2.services.UserService;

/* 
 * USER'S PROFILE
 * This Controller will only be accessed when a user is logged into their account
 * 
 */

@RestController
@RequestMapping("/user")
public class UserController {

	private UserService usrService;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	public UserController(UserService usrService) {
		this.usrService = usrService;
	}

	/*
	 * //get user profile
	 * 
	 * @GetMapping("/users") public String getUsers() {
	 * //logger.info("Entered get user profile info with username: "); return
	 * "returning dummy string"; }
	 */
		//get user profile
//		@GetMapping("/{userName}")
//		public GuestInformation getUserProfile(@PathVariable String userName)
//		{
//			logger.info("Entered get user profile info with username: "+ userName);
//			return usrService.findUserProfile(userName);
//		}

		@GetMapping
		public Principal getPrincipal(Principal principal)
		{
			return principal;
		}

		
		@GetMapping("/profile")
		public GuestInformation getUser(@AuthenticationPrincipal GuestInformation user)
		{
			return user;
		}
		
//		//edit user profile
//		@PutMapping("/profile/{userName}")
//		public ResponseEntity<GuestInformation> editUserProfile(@PathVariable String userName, @RequestBody GuestInformation gi)
//		{
//			boolean result = usrService.editUserProfile(gi, userName);
//			
//			if(result == true)
//			{
//				return new ResponseEntity<>(HttpStatus.ACCEPTED);
//			}
//			else {
//				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//			}
//			
//			
//		}
//		
		
		//edit user profile
		@PutMapping("/profile/{userName}")
		public ResponseEntity<GuestInformation> editUserProfile(@RequestBody GuestInformation gi, @PathVariable String userName)
		{
			boolean status = usrService.editUserProfile(gi, userName);
			
			if(status)
			{
				return new ResponseEntity<>(HttpStatus.ACCEPTED);
			}
			else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

		}

}
