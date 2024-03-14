package com.demo.SpringSecurityDB.service;

import java.util.List;

import com.demo.SpringSecurityDB.model.User;



public interface UserService {

	User saveUSer(User user);

	List<User> getAllUsers();

	User getUserById(Integer id);

	User updateUser(User user, Integer id);

	void deleteUserById(Integer id);


	


	


	

	

	

}
