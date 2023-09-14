package com.skillstorm.project2.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skillstorm.project2.models.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
	
	//custom methods
	
	//get all reservations by user_id
	@Query("SELECT rsvp FROM Reservation rsvp WHERE rsvp.guestId.username = :userName")
	public Set<Reservation> findByUserName(@Param("userName") String userName);

}
