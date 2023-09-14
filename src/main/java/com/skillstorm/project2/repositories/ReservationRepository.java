package com.skillstorm.project2.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.project2.models.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
	
	//custom methods
	
	//get all reservations by user_id
	public Set<Reservation> findByGuestId(long guestId);

}
