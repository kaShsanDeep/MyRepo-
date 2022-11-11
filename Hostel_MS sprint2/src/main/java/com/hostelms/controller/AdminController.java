
package com.hostelms.controller;

import java.util.List;

import com.hostelms.exception.GlobalException;
import com.hostelms.model.Room;
import com.hostelms.model.User;
import com.hostelms.model.login;
import com.hostelms.modeldto.RoomDTO;
import com.hostelms.modeldto.UserDTO;
import com.hostelms.service.RoomService;
import com.hostelms.service.UserService;
import com.hostelms.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {



	@Autowired
	AuthenticationManager authManager;
	@Autowired
	private UserService userService;
	@Autowired
	private RoomService roomService;
	@Autowired
	JwtUtil jwt;

	// LOGIN REQUEST
	@PostMapping("/login")
	public String generateToken(@RequestBody login log) {

		authManager.authenticate(new UsernamePasswordAuthenticationToken(log.getUserName(), log.getUserPassword()));
		return jwt.generateToken(log.getUserName());

	}

	
	// MAPPING METHOD 1
	@GetMapping("/login/{userId}/{userName}/{userPassword}")
	public ResponseEntity<String> login(@PathVariable int userId, String userName, String userPassword)
			throws GlobalException {
		return new ResponseEntity<>(userService.login(userId, userName, userPassword), HttpStatus.ACCEPTED);
	}

	
	// MAPPING METHOD 2
	@PostMapping("/add/user")
	public ResponseEntity<String> addUser(@RequestBody UserDTO user) throws GlobalException {
		String status = userService.addUser(user);
		if (status != null)
			return new ResponseEntity<>(status, HttpStatus.CREATED);
		else
			return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	// MAPPING METHOD 3
	@GetMapping("/fetch/all/user")
	public ResponseEntity<List<User>> getAllUser() throws GlobalException {
		return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
	}

	
	// MAPPING METHOD 4
	@GetMapping("/fetch/user/id/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable int userId) throws GlobalException {
		return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.ACCEPTED);
	}


	// MAPPING METHOD 5
	@GetMapping("/sort/user/{fields}")
	public ResponseEntity<List<User>> sortUser(@PathVariable String fields) {
		return new ResponseEntity<>(userService.sortUser(fields), HttpStatus.ACCEPTED);
	}

	
	// MAPPING METHOD 6	
	@PutMapping("/update/user")
	public ResponseEntity<User> updateUser(@RequestBody UserDTO user) {
		return new ResponseEntity<>(userService.updateUser(user), HttpStatus.ACCEPTED);
	}

	
	// MAPPING METHOD 7
	@PutMapping("/update/user/rent/{userId}/{userRent}")
	public ResponseEntity<String> updateUserRent(@PathVariable int userId, int userRent) throws GlobalException {
		return new ResponseEntity<>(userService.updateRent(userId, userRent), HttpStatus.ACCEPTED);
	}

	
	// MAPPING METHOD 8
	@PutMapping("/update/user/email/{userId}/{email}")
	public ResponseEntity<String> updateUserEmail(@PathVariable int userId, String email) throws GlobalException {
		return new ResponseEntity<>(userService.updateEmail(userId, email), HttpStatus.ACCEPTED);
	}

	
	// MAPPING METHOD 9
	@PutMapping("/update/user/contact/{userId}/{contact}")
	public ResponseEntity<String> updateContact(@PathVariable int userId, String contact) throws GlobalException {
		return new ResponseEntity<>(userService.updateContact(userId, contact), HttpStatus.ACCEPTED);
	}

	
	// MAPPING METHOD 10
	@PutMapping("/allot/room/{roomId}/{userId}")
	public ResponseEntity<String> allotUserRoom(@PathVariable int roomId, int userId) throws GlobalException {
		return new ResponseEntity<>(userService.allotRoom(roomId, userId), HttpStatus.ACCEPTED);
	}

	
	// MAPPING METHOD 11
	@DeleteMapping("/delete/user/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable int userId) throws GlobalException {
		return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.OK);
	}

	
	// MAPPING METHOD 12
	@PostMapping("/add/room")
	public ResponseEntity<String> addUser(@RequestBody RoomDTO room) throws GlobalException {
		String status = roomService.addRoom(room);
		if (status != null)
			return new ResponseEntity<>(status, HttpStatus.CREATED);
		else
			return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	// MAPPING METHOD 13
	@GetMapping("/fetch/all/room")
	public ResponseEntity<List<Room>> getAllRoom() throws GlobalException {
		return new ResponseEntity<>(roomService.getAllRoom(), HttpStatus.OK);
	}

	
	// MAPPING METHOD 14
	@GetMapping("/fetch/room/id/{roomId}")
	public ResponseEntity<Room> getRoomById(@PathVariable int roomId) throws GlobalException {
		return new ResponseEntity<>(roomService.getRoomById(roomId), HttpStatus.OK);
	}

	
	// MAPPING METHOD 15
	@DeleteMapping("/delete/room/{roomId}")
	public ResponseEntity<String> deleteRoom(@PathVariable int roomId) throws GlobalException {
		return new ResponseEntity<>(roomService.deleteRoom(roomId), HttpStatus.OK);
	}
}
