package com.skillstorm.project2.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfigurations {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http.authorizeHttpRequests((authorizeHttpRequests) -> {
			
			authorizeHttpRequests
			.mvcMatchers("/homepage").permitAll()
			.mvcMatchers("/homepage/signup").permitAll()
			.mvcMatchers("/user/").authenticated()
			.mvcMatchers("/user/**").hasAuthority("ROLE_USER");
		}).httpBasic();
		
		http.csrf((csrf) -> {
			csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
			.ignoringAntMatchers("/homepage/signup");
		});
		
		http.csrf((csrf) -> {
			csrf.ignoringAntMatchers("/user/profile/{id}");
		});
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
		
		return passwordEncoder;
	}

}
