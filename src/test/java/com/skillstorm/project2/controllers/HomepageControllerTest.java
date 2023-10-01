package com.skillstorm.project2.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.skillstorm.project2.models.Cabin;
import com.skillstorm.project2.models.GuestInformation;
import com.skillstorm.project2.services.CabinService;
import com.skillstorm.project2.services.UserService;

@WebMvcTest(HomepageController.class)
class HomepageControllerTest {

	@Autowired
	private MockMvc mockmvc;
	
	@MockBean
	private CabinService cabinService;
	
	@Autowired
	private WebApplicationContext context;

	@MockBean
	private UserService userService;
	
	@BeforeEach
	public void setUp() throws Exception {
		 MockitoAnnotations.openMocks(this);

			mockmvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()) // Apply Spring Security
																							// configuration
					.build();
		
	}

	@WithMockUser(value = "ROLE_USER")
	@Test
	public void testGetCabinByStateId() throws Exception{
		
		List<Cabin> cabins = new ArrayList<>();
		
		when(cabinService.getCabinByStateId("TX"))
		.thenReturn(cabins);
		
		this.mockmvc.perform(get("/homepage/TX"))
		.andExpect(status().isOk());
	}

	  @WithMockUser(value = "ROLE_USER")
	  @Test 
	  public void testGetCabinById() throws Exception{ 
		  
		  Cabin cabin = new Cabin();
		  
		  when(cabinService.getCabinById(3))
			.thenReturn(cabin);
			
			this.mockmvc.perform(get("/homepage/reserve/3"))
			.andExpect(status().isOk());
			
		  
	  }
	  
	  @WithMockUser(value = "ROLE_USER")
	  @Test 
	  public void testGetUserByUserName() throws Exception{ 
		  
		  when(userService.getUserByUsername("mross"))
			.thenReturn("mross");
			
			this.mockmvc.perform(get("/homepage/newuser/mross"))
			.andExpect(status().isOk());
		  //userService.getUserByUsername(username);
	  }
	  
	  @WithMockUser(value = "ROLE_USER")
	  @Test void testRegisterUser() throws Exception{ 
		  
		  GuestInformation gi1 = new GuestInformation(6, "Vasantala", "Phani is awesome", "pvasantala", "secret567",
					"pvasantala@skillstorm.com", "56546465", "1234 main street UPDATED5", "English", "ROLE_USER");
	  

			
		  when(userService.registerUser(gi1))
		  .thenReturn("New User Registered");

      
        mockmvc.perform(MockMvcRequestBuilders.post("/homepage/signup").with(csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON_VALUE).characterEncoding("UTF-8")
                .content("{"
                		+ ""
                		+ "        \"username\": \"user\","
                		+ ""
                		+ "        \"password\": \"secret123\","
                		+ ""
                		+ "        \"email\": \"pvasantala@skillstorm.com\","
                		+ ""
                		+ "        \"phone\": \"56546465\","
                		+ ""
                		+ "        \"address\": \"1234 main street UPDATED5\","
                		+ ""
                		+ "        \"language\": \"English\","
                		+ ""
                		+ "        \"role\": \"ADMIN\","
                		+ ""
                		+ "        \"last_name\": \"Vasantala\","
                		+ ""
                		+ "        \"first_name\": \"Phani is awesome\""
                		+ ""
                		+ "}"))
        		.andDo(print())
               .andExpect(MockMvcResultMatchers.status().isCreated());

        
        
	
	  }
	 

}
