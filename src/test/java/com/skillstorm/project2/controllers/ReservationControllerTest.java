package com.skillstorm.project2.controllers;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.skillstorm.project2.models.Amenity;
import com.skillstorm.project2.models.Cabin;
import com.skillstorm.project2.models.CabinLocations;
import com.skillstorm.project2.models.GuestInformation;
import com.skillstorm.project2.models.Image;
import com.skillstorm.project2.models.Reservation;
import com.skillstorm.project2.services.ReservationService;

@WebMvcTest(ReservationController.class)
class ReservationControllerTest {

	@Autowired
	private MockMvc mockmvc;

	@Autowired
	private WebApplicationContext context;

	@MockBean
	private ReservationService rsvpService;

	@InjectMocks
    private ReservationController reservationController;
	
	Reservation r1;
	Reservation r2;
	List<Reservation> rList = new ArrayList<>();

	@BeforeEach
	void setUp() throws Exception {
		
		 // Initialize mocks and inject them into the controller
        MockitoAnnotations.openMocks(this);

		mockmvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()) // Apply Spring Security
																						// configuration
				.build();

		Amenity amenities1 = new Amenity(1, true, true, true, true, false, true, true);

		Image img = new Image(1,
				"https://www.visitmysmokies.com/wp-content/uploads/2019/01/Altitude-Adjustment.jpeg,https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQmIBQ8LXpCMJ9U0ycO4PegttqhmLKv7RlLJA&usqp=CAU,https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRs29OC0EovPsg5Lfj_9TJxlkFr9rxCffBDMA&usqp=CAU,https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT5NxUbdpmcYojjPaZKBVbQpi_Hm5yf0WKngw&usqp=CAU,https://images.squarespace-cdn.com/content/v1/606c6e8431a9a703623d772e/1678673333195-X84F6D3230U8BD10247W/Magical+Mountain+Resorts+Caretaker%27s+Cabin+Bathroom.JPG");

		CabinLocations cabinLoc = new CabinLocations(null, null, null, null, null);

		Cabin cabin1 = new Cabin(5, "Willow Wind Lodge", 6, 4, 21, 269.99, "family - mega size", 10, amenities1, img,
				cabinLoc);

		GuestInformation gi1 = new GuestInformation(6, "Vasantala", "Phani is awesome", "pvasantala", "secret567",
				"pvasantala@skillstorm.com", "56546465", "1234 main street UPDATED5", "English", "ROLE_USER");

		GuestInformation gi2 = new GuestInformation(2, "RossR", "Mike", "mross", "secret", "randomuser23@gmail.com",
				"1234512345", "789 Oak St", "English", "ROLE_USER");

		r1 = new Reservation(15, "Wed Sep 06 2023", "Fri Oct 06 2023", cabin1, gi1);
																					

		r2 = new Reservation();

		r2.setCheck_in("Wed Sep 29 2023");
		r2.setCheck_out("Fri Oct 06 2023");
		r2.setGuest_id(gi2);
		r2.setReserved_cabin_id(cabin1);

		rList.add(r1);
		

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@WithMockUser(value = "ROLE_USER")
	@Test
	void testGetReservationById() throws Exception {
		when(rsvpService.getReservationById(15))
		.thenReturn(r1);
	this.mockmvc.perform(get("/user/reservations/reservation/15"))
		.andExpect(status().isOk());
	
	}

	@WithMockUser(value = "ROLE_USER")
	@Test
	void testGetAllReservationsByUserName() throws Exception {
		
		when(rsvpService.getAllReservations("pvasantala"))
			.thenReturn(rList);
		this.mockmvc.perform(get("/user/reservations/pvasantala"))
			.andExpect(status().isOk());
	}

	@Test void testGetAllReservationsByUserName_Unauthorized() throws Exception {
	  
	  when(rsvpService.getAllReservations("pvasantala")) .thenReturn(rList);
	  this.mockmvc.perform(get("/user/reservations/pvasantala"))
	  .andExpect(status().isUnauthorized()); }

	@Test
    void testEditReservation_accepted() {
        long reservationId = 1L;
        Reservation reservation = new Reservation(); // Create a valid reservation object

        // Mock the service method to return true (accepted)
        when(rsvpService.editReservation(reservation, reservationId)).thenReturn(true);

        ResponseEntity<Reservation> responseEntity = reservationController.editReservation(reservationId, reservation);

        // Verify the response status and body
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
    }
    

	
	 
	@WithMockUser(value = "ROLE_USER")
	@Test
	void testDeleteReservation()throws Exception  {
		
		when(rsvpService.deleteReservation(1))
		 .thenReturn("Deleted Sucessfully");
		
		 this.mockmvc.perform(delete("/user/reservations/14").with(csrf().asHeader()))
		 .andDo(print())
		 .andExpect(status().isOk());

	}
	
	
	@WithMockUser(value = "ROLE_USER")
	@Test
	void testCreateNewReservation() throws Exception {
		
		  when(rsvpService.createNewReservation(r2)).thenReturn("Reserved the cabin");

      
        mockmvc.perform(MockMvcRequestBuilders.post("/user/reservations").with(csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON_VALUE).characterEncoding("UTF-8")
                .content(""
                		+ ""
                		+ "{"
                		+ ""
                		+ "    \"reserved_cabin_id\": {"
                		+ ""
                		+ "        \"id\": 14,"
                		+ ""
                		+ "        \"sleeps\": 21,"
                		+ ""
                		+ "        \"price\": 269.99,"
                		+ ""
                		+ "        \"description\": \"family - mega size\","
                		+ ""
                		+ "        \"capacity\": 10,"
                		+ ""
                		+ "        \"cabinloc\": {"
                		+ ""
                		+ "            \"stateId\": \"WA\","
                		+ ""
                		+ "            \"address\": \"1234 Elm St\","
                		+ ""
                		+ "            \"city\": \"Seattle\","
                		+ ""
                		+ "            \"zip\": \"98101\""
                		+ ""
                		+ "        },"
                		+ ""
                		+ "        \"cabin_name\": \"Willow Wind Lodge\","
                		+ ""
                		+ "        \"image_id\": {"
                		+ ""
                		+ "            \"id\": 1,"
                		+ ""
                		+ "            \"url\": \"https://www.visitmysmokies.com/wp-content/uploads/2019/01/Altitude-Adjustment.jpeg,https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQmIBQ8LXpCMJ9U0ycO4PegttqhmLKv7RlLJA&usqp=CAU,https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRs29OC0EovPsg5Lfj_9TJxlkFr9rxCffBDMA&usqp=CAU,https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT5NxUbdpmcYojjPaZKBVbQpi_Hm5yf0WKngw&usqp=CAU,https://images.squarespace-cdn.com/content/v1/606c6e8431a9a703623d772e/1678673333195-X84F6D3230U8BD10247W/Magical+Mountain+Resorts+Caretaker%27s+Cabin+Bathroom.JPG\""
                		+ ""
                		+ "        },"
                		+ ""
                		+ "        \"no_rooms\": 6,"
                		+ ""
                		+ "        \"amenities_id\": {"
                		+ ""
                		+ "            \"id\": 1,"
                		+ ""
                		+ "            \"patio\": true,"
                		+ ""
                		+ "            \"fireplace\": true,"
                		+ ""
                		+ "            \"kitchen\": true,"
                		+ ""
                		+ "            \"jacuzzi\": false,"
                		+ ""
                		+ "            \"outdoor_hot_shower\": true,"
                		+ ""
                		+ "            \"outdoor_furniture\": true,"
                		+ ""
                		+ "            \"pet_friendly\": true"
                		+ ""
                		+ "        },"
                		+ ""
                		+ "        \"no_bathrooms\": 4"
                		+ ""
                		+ "    },"
                		+ ""
                		+ "    \"check_in\": \"09/23/2023\","
                		+ ""
                		+ "    \"check_out\": \"10/01/2023\","
                		+ ""
                		+ "    \"guest_id\": {"
                		+ ""
                		+ "        \"id\": 6,"
                		+ ""
                		+ "        \"username\": \"pvasantala\","
                		+ ""
                		+ "        \"password\": \"$2a$10$uUMo3mTsc51hKI8VlFb1iujzKwT5ThOzztP3HX7fWtHZj8oguXva2\","
                		+ ""
                		+ "        \"email\": \"pvasantala@skillstorm.com\","
                		+ ""
                		+ "        \"phone\": \"56546465\","
                		+ ""
                		+ "        \"address\": \"1234 main street UPDATED5\","
                		+ ""
                		+ "        \"language\": \"English\","
                		+ ""
                		+ "        \"role\": \"ROLE_USER\","
                		+ ""
                		+ "        \"last_name\": \"Vasantala\","
                		+ ""
                		+ "        \"first_name\": \"Phani is awesome\""
                		+ ""
                		+ "    }"
                		+ ""
                		+ "}"
                		+ ""))
        		.andDo(print())
               .andExpect(MockMvcResultMatchers.status().isCreated());

        
        
	}

	
	 
	 
    

}
