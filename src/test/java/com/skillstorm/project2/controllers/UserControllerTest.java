package com.skillstorm.project2.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.skillstorm.project2.models.GuestInformation;
import com.skillstorm.project2.services.UserService;

@WebMvcTest(UserController.class)
class UserControllerTest {

	@Autowired
	private MockMvc mockmvc;
	
	@Autowired
	private WebApplicationContext context;

	@MockBean
	private UserService usrService;
	
	@InjectMocks
	private UserController userController;
	
	@BeforeEach
	void setUp() throws Exception {
		

		 MockitoAnnotations.openMocks(this);

			mockmvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity())
					.build();
		
	
	}

	
	@WithMockUser(value = "ROLE_USER")
	@Test
	void testGetUser() throws Exception{
		
		this.mockmvc.perform(get("/user/profile"))
		.andExpect(status().isOk());
		
	}

	@WithMockUser(value = "ROLE_USER")
	@Test
	void testGetUserById() throws Exception
	{
		GuestInformation gi1 = new GuestInformation(6, "Vasantala", "Phani is awesome", "pvasantala", "secret567",
				"pvasantala@skillstorm.com", "56546465", "1234 main street UPDATED5", "English", "ROLE_USER");
		  
		  when(usrService.findByUserId(6))
			.thenReturn(gi1);
			
			this.mockmvc.perform(get("/user/profile/6"))
			.andExpect(status().isOk());
	}

	@WithMockUser(value = "ROLE_USER")
	@Test
	void testEditUserProfile() throws Exception{
		
		GuestInformation gi1 = new GuestInformation(6, "Vasantala", "Phani is awesome", "pvasantala", "secret567",
				"pvasantala@skillstorm.com", "56546465", "1234 main street UPDATED5", "English", "ROLE_USER");

		
        long userId = 6L;
        

        // Mock the service method to return true (accepted)
        when(usrService.editById(gi1, userId)).thenReturn(gi1);

        GuestInformation gi2 = userController.editUserProfile(gi1,6);

        // Verify the response status and body
        assertEquals(gi1, gi2);
        
        
    
        
		
	}

}
