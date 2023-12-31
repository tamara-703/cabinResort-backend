package com.skillstorm.project2.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skillstorm.project2.exceptions.UserAlreadyExistsException;
import com.skillstorm.project2.models.GuestInformation;
import com.skillstorm.project2.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

	private UserRepository usrRepo;
	private PasswordEncoder passwordEncoder;
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	public UserService(UserRepository usrRepo, PasswordEncoder passwordEncoder) {
		this.usrRepo = usrRepo;
		this.passwordEncoder = passwordEncoder;
	}

	public GuestInformation findUserProfile(String userName) {

		logger.info(userName);
		
		GuestInformation gi = usrRepo.findByUserName(userName);

		logger.info("guest info: "+gi);
		return gi;
	}

	public GuestInformation editById(GuestInformation guestInfo, long id) {
		
		GuestInformation existingUser = usrRepo.findById(id).get();

		if (existingUser != null && existingUser.getId() == id) {
			guestInfo.setId(id);
			guestInfo.setPassword(passwordEncoder.encode(guestInfo.getPassword()));
			guestInfo.setRole("ROLE_USER");
			return usrRepo.save(guestInfo);
		}
		
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		GuestInformation user = usrRepo.findByUserName(username);
		
		if(user == null)
		{
			new UsernameNotFoundException("Username " + username + " does not exist.");
		}

		return user;
	}
	
	//user signup
	public String registerUser(GuestInformation guestInfo) throws UserAlreadyExistsException
	{
		String existingUser = guestInfo.getUsername();
		boolean isUserExists = usrRepo.existsByUsername(existingUser);
		
		if(isUserExists)
		{
			throw new UserAlreadyExistsException("Username " + existingUser + " already exists.");
		}
		
		guestInfo.setPassword(passwordEncoder.encode(guestInfo.getPassword()));
		guestInfo.setRole("ROLE_USER");
		
		usrRepo.save(guestInfo);
		
		return "New User Registered";
	}
	
	public GuestInformation findByUserId(long id)
	{
		return usrRepo.findById(id).get();
	}
	
	public GuestInformation getUserByUsername(String username)
	{
		return usrRepo.findByUserName(username);
	}
	
	public Optional<GuestInformation> getRegisteredUser(long id)
	{
		logger.info("GETTING REGISTERED USER");
		return usrRepo.findById(id);
	}

}
