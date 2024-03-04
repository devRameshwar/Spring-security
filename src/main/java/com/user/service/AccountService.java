package com.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.user.entity.User;
import com.user.repository.UserRepository;
import com.user.request.RegistretionRequest;
import com.user.responce.RegisterResponce;

@Service
public class AccountService {

	@Autowired
	private UserRepository repository;

	public RegisterResponce registerUser(RegistretionRequest request) {
		 User user = new User(request.getUserId(),request.getUserName(), request.getEmail(), request.getPassword(), request.getMobileNumber());
		 repository.save(user);
		 
		 RegisterResponce responce = new RegisterResponce();
		 responce.setEmail(request.getEmail());
		 responce.setMassege("User Registretion Doen successfully.....!");
 
		return responce;
	}

}
