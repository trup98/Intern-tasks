package com.demo.auth2server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.auth2server.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByusername(String username);

}
