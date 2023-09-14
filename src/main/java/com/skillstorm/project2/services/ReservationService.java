package com.skillstorm.project2.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.project2.models.Reservation;
import com.skillstorm.project2.repositories.ReservationRepository;

@Service
public class ReservationService {
	
	private ReservationRepository rsvpRepo;
	
	@Autowired
	public ReservationService (ReservationRepository rsvpRepo)
	{
		this.rsvpRepo = rsvpRepo;
	}
	
	//get reservation by id
	public Reservation getReservationById(long id)
	{
		return rsvpRepo.findById(id).get();
	}
	
	//get all reservations by user_id
	public Set<Reservation> getAllReservations(long guestId)
	{
		return rsvpRepo.findByGuestId(guestId);
	}
	
	
	//post a reservation
	public void createNewReservation(Reservation reservation)
	{
		rsvpRepo.save(reservation);
	}
	
	//edit a reservation
	public boolean editReservation(Reservation reservation, long id)
	{
		boolean result = false;
		Reservation existingReservation = rsvpRepo.findById(id).get();
		
		if(existingReservation.getId() == id)
		{
			rsvpRepo.save(reservation);
			result = true;
		}
		
		return result;
	}
	
	//delete a reservation
	public void deleteReservation(long id)
	{
		rsvpRepo.deleteById(id);
	}

}
