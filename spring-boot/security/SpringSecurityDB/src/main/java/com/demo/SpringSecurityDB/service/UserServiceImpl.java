package com.demo.SpringSecurityDB.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.SpringSecurityDB.exception.ResourseNotFoundException;
import com.demo.SpringSecurityDB.model.User;
import com.demo.SpringSecurityDB.repositery.UserRepositery;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepositery userRepositery;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User saveUSer(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return this.userRepositery.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return this.userRepositery.findAll();
	}

	@Override
	public User getUserById(Integer id) {
		return userRepositery.findById(id)
				.orElseThrow(() -> new ResourseNotFoundException("User Not Found With ID : " + id));
	}

	@Override
	public User updateUser(User user, Integer id) {
		User exUser = userRepositery.findById(id)
				.orElseThrow(() -> new ResourseNotFoundException("User Not Found With ID : " + id));
		exUser.setId(id);

		return this.userRepositery.save(user);

	}

	@Override
	public void deleteUserById(Integer id) {
		User user = userRepositery.findById(id)
				.orElseThrow(() -> new ResourseNotFoundException("User Not Found With id :" + id));

		userRepositery.delete(user);
	}

}
