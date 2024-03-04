package com.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class ApplicationSecurityConfigration {

	Logger logger = LoggerFactory.getLogger(ApplicationSecurityConfigration.class);
	@Autowired
	private TokenFilter tokenFilter;

	@Bean
	AuthenticationManager getAuthenticatinManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {

		logger.info("Initialigetion Bean AuthenticationManager ");
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	BCryptPasswordEncoder getBCryptPasswordEncoder() {

		logger.info("Initialigetion Bean BCryptPasswordEncoder ");
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(csrf -> csrf.disable()).cors(cors -> cors.disable())
				.authorizeHttpRequests(auth -> auth.requestMatchers("account/log-In","/account/welcome", "/account/register").permitAll().anyRequest().authenticated())
				.addFilterBefore(this.tokenFilter, UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
	}

}
