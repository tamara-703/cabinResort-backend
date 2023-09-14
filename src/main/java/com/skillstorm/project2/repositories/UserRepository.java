package com.skillstorm.project2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skillstorm.project2.models.GuestInformation;

@Repository
public interface UserRepository extends JpaRepository<GuestInformation,Long>{
	
	//custom methods (if any)
	//public GuestInformation findByUserName(String username);
	
	@Query("SELECT gi FROM GuestInformation gi WHERE gi.username = ?1")
	public GuestInformation findByUserName(String userName);
	
}
