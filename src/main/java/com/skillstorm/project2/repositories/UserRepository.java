package com.skillstorm.project2.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skillstorm.project2.models.GuestInformation;

@Repository
public interface UserRepository extends JpaRepository<GuestInformation,Long>{
	

	//custom methods (if any)
	
	//find guest information by their username
	@Query("SELECT gi FROM GuestInformation gi WHERE gi.username = :userName")
	public GuestInformation findByUserName(@Param("userName")String userName);
	
	//for user login
	public Optional<GuestInformation> findByUsername(String username);
	
	//verify if user exists in database
	public boolean existsByUsername(String username);
	
	@Query("SELECT gi.username FROM GuestInformation gi WHERE gi.username = :username")
	public String findUserByUsername(String username);
	
}
