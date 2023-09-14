package com.skillstorm.project2.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.project2.models.GuestInformation;
import com.skillstorm.project2.services.UserService;

@RestController
@RequestMapping("/profile")
public class UserController {

	private UserService usrService;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
		public UserController(UserService usrService) {
		super();
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
		@GetMapping("/users/{userName}")
		public GuestInformation getUserProfile(@PathVariable String userName)
		{
			logger.info("Entered get user profile info with username: "+userName);
			return usrService.findUserProfile(userName);
		}
		
		
		//edit user profile
		@PutMapping("users/{userName}")
		public ResponseEntity<GuestInformation> editUserProfile(@PathVariable String userName, @RequestBody GuestInformation gi)
		{
			boolean result = usrService.editUserProfile(gi, userName);
			
			if(result == true)
			{
				return new ResponseEntity<>(HttpStatus.ACCEPTED);
			}
			else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
			
		}
}
