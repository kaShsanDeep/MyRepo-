
package com.hostelms.controller;

import com.hostelms.exception.GlobalException;
import com.hostelms.model.User;
import com.hostelms.model.login;
import com.hostelms.modeldto.UserDTO;
import com.hostelms.service.UserService;
import com.hostelms.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	AuthenticationManager authManager;

	@Autowired
	JwtUtil jwt;

	// LOGIN REQUEST
	@PostMapping("/login")
	public String generateToken(@RequestBody login log) {

		authManager.authenticate(new UsernamePasswordAuthenticationToken(log.getUserName(), log.getUserPassword()));
		return jwt.generateToken(log.getUserName());

	}

	// MAPPING METHOD 
	// TO LOGIN USING USERNAME AND PASSWORD
	@GetMapping("/login/{userId}/{userName}/{userPassword}")
	public ResponseEntity<String> login(@PathVariable int userId, String userName, String userPassword)
			throws GlobalException {
		return new ResponseEntity<>(userService.login(userId, userName, userPassword), HttpStatus.ACCEPTED);
	}

	// MAPPING METHOD 
	// TO ADD NEW USER
	@PostMapping("/add")
	public ResponseEntity<String> addUser(@RequestBody UserDTO user) throws GlobalException {
		String status = userService.addUser(user);
		if (status != null)
			return new ResponseEntity<>(status, HttpStatus.CREATED);
		else
			return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// MAPPING METHOD 
	// TO FETCH USER FROM DATABASE USING PRIMARY KEY
	@GetMapping("/fetch/id/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable int userId) throws GlobalException {
		return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.ACCEPTED);
	}

	// MAPPING METHOD 
	// TO UPDATE THE DETAILS OF EXISTING USER IN DATABASE
	@PutMapping("/update")
	public ResponseEntity<User> updateUser(@RequestBody UserDTO user) {
		return new ResponseEntity<>(userService.updateUser(user), HttpStatus.ACCEPTED);
	}

	// MAPPING METHOD 
	// TO UPDATE OR SET THE RENT AMOUNT OF AN USER IN DATABASE
	@PutMapping("/update/rent/{userId}/{userRent}")
	public ResponseEntity<String> updateUserRent(@PathVariable int userId, int userRent) throws GlobalException {
		return new ResponseEntity<>(userService.updateRent(userId, userRent), HttpStatus.ACCEPTED);
	}

	// MAPPING METHOD
	// TO CHANGE AND UPDATE THE EMAIL ADDRESS OF AN USER IN DATABASE
	@PutMapping("/update/email/{userId}/{email}")
	public ResponseEntity<String> updateUserEmail(@PathVariable int userId, String email) throws GlobalException {
		return new ResponseEntity<>(userService.updateEmail(userId, email), HttpStatus.ACCEPTED);
	}

	// MAPPING METHOD
	// TO CHANGE AND UPDATE THE CONTACT INFO OF AN USER
	@PutMapping("/update/contact/{userId}/{contact}")
	public ResponseEntity<String> updateContact(@PathVariable int userId, String contact) throws GlobalException {
		return new ResponseEntity<>(userService.updateContact(userId, contact), HttpStatus.ACCEPTED);
	}
}
