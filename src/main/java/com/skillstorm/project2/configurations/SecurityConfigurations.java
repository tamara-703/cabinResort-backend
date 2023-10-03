package com.skillstorm.project2.configurations;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


/*
 * Enabling Spring security for the application
 * */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfigurations {
	
	/* Setting the patterns to which authorization is required
	 * Also enables the csrf settings
	 * */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http.authorizeHttpRequests((authorizeHttpRequests) -> {
			
			authorizeHttpRequests
			.mvcMatchers("/homepage").permitAll()
			.mvcMatchers("/homepage/signup").permitAll()
			.mvcMatchers("/homepage/newuser/{username}").permitAll()
			.mvcMatchers("/user/").authenticated()
			.mvcMatchers("/user/**").hasAuthority("ROLE_USER");
		}).httpBasic();
		
		//enabling users to signup by ignoring any unsafe POST requests to the specified path
		http.csrf((csrf) -> {
			csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
			.ignoringAntMatchers("/homepage/signup")
			.ignoringAntMatchers("/user/reservations");
		});
		

		//enabling users to edit their profile by ignoring any unsafe PUT requests to the specified path
		http.csrf((csrf) -> {
			csrf.ignoringAntMatchers("/user/profile/{id}");
			csrf.ignoringAntMatchers("/user/reservations");
			csrf.ignoringAntMatchers("/user/reservations/{id}");
			csrf.ignoringAntMatchers("/user/reservations/{rsvpId}");
		});
		
		http.cors((cors) -> {
			cors.configurationSource(req -> corsConfiguration());
		});
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
		
		return passwordEncoder;
	}
	
	/*
	 * Setting the CORS configurations
	 * */
	private CorsConfiguration corsConfiguration() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://project2-cabin-fever.s3-website-us-east-1.amazonaws.com/")); // Add your allowed origins //http://project2-cabin-fever.s3-website-us-east-1.amazonaws.com/
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization"));
		configuration.setAllowCredentials(false); // Allow cookies and credentials
		configuration.setMaxAge(3600L);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return configuration;
	}

}
