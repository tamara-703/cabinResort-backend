package com.skillstorm.project2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skillstorm.project2.models.Cabin;

@Repository
public interface CabinRepository extends JpaRepository<Cabin, Long> {
	
	//custom methods
	@Query("SELECT c FROM Cabin c WHERE c.cabinloc.stateId = :stateId")
	public List<Cabin> findByStateId(@Param("stateId") String stateId);

}
