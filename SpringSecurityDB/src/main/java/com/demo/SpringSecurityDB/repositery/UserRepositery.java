package com.demo.SpringSecurityDB.repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.SpringSecurityDB.model.User;

@Repository
public interface UserRepositery extends JpaRepository<User, Integer> {

	User getByusername(String username);

}
