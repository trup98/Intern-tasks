package com.demo.SpringSecurityDB.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.SpringSecurityDB.model.CustomUserDetails;
import com.demo.SpringSecurityDB.model.User;
import com.demo.SpringSecurityDB.repositery.UserRepositery;

@Service

public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepositery userRepositery;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = this.userRepositery.getByusername(username);

		if (user == null) {
			throw new UsernameNotFoundException("No User Exist With This Username");
		}
		return new CustomUserDetails(user);
	}

}
