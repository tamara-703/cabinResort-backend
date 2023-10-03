package com.skillstorm.project2.services;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.project2.models.Reservation;
import com.skillstorm.project2.repositories.ReservationRepository;

@Service
public class ReservationService {
	
	private ReservationRepository rsvpRepo;
	private EmailService emailService;
	
	@Autowired
	public ReservationService (ReservationRepository rsvpRepo, EmailService emailService)
	{
		this.rsvpRepo = rsvpRepo;
		this.emailService = emailService;
	}
	
	//get reservation by id
	public Reservation getReservationById(long id)
	{
		return rsvpRepo.findById(id).get();
	}
	
	//get all reservations by username
	public List<Reservation> getAllReservations(String userName)
	{
		return rsvpRepo.findByUserName(userName);
	}
	
	
	//post a reservation
	public String createNewReservation(Reservation reservation)
	{
		rsvpRepo.save(reservation);
		
		sendEmail(reservation);
		return "Reserved the cabin";
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
	public String deleteReservation(long id)
	{
		rsvpRepo.deleteById(id);
		
		return "Deleted Sucessfully";
	}
	private String sendEmail(Reservation reservation) {
        try {
			emailService.sendHtmlMessage(reservation);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "Email sent successfully.";
    }
	
}
