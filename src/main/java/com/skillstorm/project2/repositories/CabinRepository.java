package com.skillstorm.project2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.project2.models.Cabin;

@Repository
public interface CabinRepository extends JpaRepository<Cabin, Long> {
	
	//custom methods

}
