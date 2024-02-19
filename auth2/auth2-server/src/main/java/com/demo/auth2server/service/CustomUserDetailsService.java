package com.demo.auth2server.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.auth2server.model.CustomUserDetails;
import com.demo.auth2server.model.User;
import com.demo.auth2server.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> userDetails = this.userRepository.findByusername(username);

		userDetails.orElseThrow(() -> new UsernameNotFoundException("No User Exist With This Username"));

		return userDetails.map(CustomUserDetails::new).get();
	}

}
