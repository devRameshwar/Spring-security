package com.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.user.entity.User;
import com.user.repository.UserRepository;

@Service
public class UserAuthenticationServiceImp implements UserDetailsService {

	Logger logger = LoggerFactory.getLogger(UserAuthenticationServiceImp.class);

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		logger.info("User Logger Details...");
		User user = repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User Not Found "));
		return user;
	}

}
