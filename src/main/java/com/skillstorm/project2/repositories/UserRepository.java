package com.skillstorm.project2.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skillstorm.project2.models.GuestInformation;

@Repository
public interface UserRepository extends JpaRepository<GuestInformation,Long>{
	
<<<<<<< HEAD
	//custom methods (if any)
	
	@Query("SELECT gi FROM GuestInformation gi WHERE gi.username = :username")
	public GuestInformation findByUsername(@Param("username")String username);
=======
	//custom methods
	
	//find guest information by their username
	@Query("SELECT gi FROM GuestInformation gi WHERE gi.username = :userName")
	public GuestInformation findByUserName(@Param("userName")String userName);
>>>>>>> 29acb325bcd6ebe850274ca3c4959055b0df982a
	
	//for user login
//	public Optional<GuestInformation> findByUsername(String username);
	
	//verify if user exists in database
	public boolean existsByUsername(String username);
	
}