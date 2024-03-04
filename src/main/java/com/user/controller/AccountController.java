package com.user.controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwtToken.TokenHelper;
import com.user.request.LogInRequest;
import com.user.request.RegistretionRequest;
import com.user.responce.LogInResponce;
import com.user.responce.RegisterResponce;
import com.user.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/account")
public class AccountController {

	@Autowired
	private TokenHelper tokenHelper;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AccountService service;

	Logger logger = LoggerFactory.getLogger(AccountController.class);

	@GetMapping(path = "/welcome")
	public String getWelcome() {
		return "WELCOME!";
	}

	@PostMapping(path = "/register")
	public ResponseEntity<RegisterResponce> registerUser(@RequestBody RegistretionRequest request) {
		//request.setEmail(passwordEncoder.encode(request.getEmail()));
		request.setPassword(passwordEncoder.encode(request.getPassword()));
		return new ResponseEntity<>(service.registerUser(request), HttpStatus.ACCEPTED);
	}
	
	@PostMapping(path = "/log-In")
	public ResponseEntity<LogInResponce> postMethodName(@RequestBody LogInRequest request) {
		
		System.err.println("login request come....");
		 
		this.doAuthenticate(request.getEmail(),request.getPassword());
		
		return new ResponseEntity<>(new LogInResponce(tokenHelper.generateToken(request.getEmail()),"  User log-In successfull "),HttpStatus.OK) ;
	}

	private void doAuthenticate(String email, String password) {
		
		 logger.info("Authentication of user cradentials....");
		 System.err.println("goes to varifecation..");
		 UsernamePasswordAuthenticationToken  authentication = new UsernamePasswordAuthenticationToken(email, password);
		 System.err.println(authentication);
		 try {
			 authenticationManager.authenticate(authentication);
			 System.err.println(authentication);
		 }catch (BadCredentialsException e) {
			 throw new RuntimeException("Invailed User & Password..");
		}
		
	}
	

}
