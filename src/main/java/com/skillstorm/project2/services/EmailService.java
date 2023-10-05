package com.skillstorm.project2.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.skillstorm.project2.models.Reservation;

@Service
public class EmailService {

	Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * This method sends an email notification to the customer about the reservation details at the Cabin Resorts
     * */
    public void sendHtmlMessage(Reservation reservation) throws MessagingException {
    	
    	String to = reservation.getGuest_id().getEmail();
    	String subject = "Your Reservation details with CabinFever Resorts!!"; 
    	
    	String htmlContent = getEmailContent(reservation);

    	logger.info("Sending email: "+htmlContent);
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        javaMailSender.send(message);
    }
    
    /*
     * This method returns the email content that has to be sent to the customer
     * */
    private String getEmailContent(Reservation reservation) {
    	
        	
        return "<html>"
        		+ " <style>"
        		+ "body{"
        		+ 	"opacity: 0.5;"
        		+ 	"background-image: url(\"https://img.sunset02.com/sites/default/files/styles/4_3_horizontal_inbody_900x506/public/image/2016/06/main/"
        		        		+ "best-cabins-in-the-west-classic-pioneer-guest-cabins-crested-butte-co-front-1113.jpg\"); "
        		+ 	"background-repeat: no-repeat; "
        		+ 	"background-size: cover;"
        		+ "}"
        		+ "</style>"
        		+ "<body >"
                + "<h1>Hello, " + reservation.getGuest_id().getFirst_name() + "!</h1>"
                + "<h3>We are glad to inform that your cabin reservation with Cabin Fever Resorts has been confirmed!!</h3>"
                + "<h4>Please find below the details.</h4>"
                + "<table border='1'>"
                + "<tr><th>Reservation number</th><th>Cabin Type</th><th>Cabin Location</th><th>Dates Booked</th><th>Amount paid</th></tr>"
                + "<tr><td>"+reservation.getId()+"</td><td>"+reservation.getReserved_cabin_id().getCabin_name()+"</td>1234 Elm St, Seattle, WA - 98101<td></td><td>"+reservation.getCheck_in()+"-"+reservation.getCheck_out()+"</td><td>$"+reservation.getReserved_cabin_id().getPrice()+"</td></tr>"
                + "</table>"
                + "</body></html>";
    }
    
}