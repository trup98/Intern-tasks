package com.demo.SpringSecurityDB.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.SpringSecurityDB.model.User;
import com.demo.SpringSecurityDB.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	// ADD USER
	@PostMapping("/add")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		user = userService.saveUSer(user);

		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	// GET ALL USERS
	//@PreAuthorize("hasRole('ADMIN')")   ///  Annotation used to authorize admin without passing any bean in Config file
	@GetMapping("/getUsers")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> user = this.userService.getAllUsers();

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Integer id) throws NotFoundException {

		User user = (User) userService.getUserById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);

	}

	// UPDATE STUDENT
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Integer id) {
		user = userService.updateUser(user, id);

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	// DELETE STUDENT BY ID
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Integer id) {
		this.userService.deleteUserById(id);
		return ResponseEntity.ok().build();
	}

}
