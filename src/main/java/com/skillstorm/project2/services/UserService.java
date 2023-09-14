package com.skillstorm.project2.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.skillstorm.project2.models.GuestInformation;
import com.skillstorm.project2.repositories.UserRepository;

@Service
public class UserService {

	private UserRepository usrRepo;
	Logger logger = LoggerFactory.getLogger(getClass());
	
	public UserService(UserRepository usrRepo) {
		super();
		this.usrRepo = usrRepo;
	}

	public GuestInformation findUserProfile(String userName) {

		logger.info(userName);
		
		GuestInformation gi = usrRepo.findByUserName(userName);

		logger.info("guest info: "+gi);
		return gi;
	}

	public boolean editUserProfile(GuestInformation guestInfo, String userName) {
		boolean result = false;
		GuestInformation existingUser = usrRepo.findByUserName(userName);

		if (existingUser.getUsername() == userName) {
			usrRepo.save(guestInfo);
			result = true;
		}

		return result;
	}

}
