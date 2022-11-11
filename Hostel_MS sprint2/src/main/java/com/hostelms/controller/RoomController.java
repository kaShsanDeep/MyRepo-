
package com.hostelms.controller;

import com.hostelms.exception.GlobalException;
import com.hostelms.model.Room;
import com.hostelms.modeldto.RoomDTO;
import com.hostelms.service.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room")
public class RoomController {

	@Autowired
	private RoomService roomService;

	// MAPPING METHOD 1
	// HANDLE THE REQUEST TO ADD NEW ROOM IN DATABASE
	@PostMapping("/add")
	public ResponseEntity<String> addUser(@RequestBody RoomDTO room) throws GlobalException {
		String status = roomService.addRoom(room);
		if (status != null)
			return new ResponseEntity<>(status, HttpStatus.CREATED);
		else
			return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// MAPPING METHOD 2
	// HANDLE THE REQUEST TO FETCH A ROOM DETAIL USING PRIMARY KEY
	@GetMapping("/fetch/id/{roomId}")
	public ResponseEntity<Room> getRoomById(@PathVariable int roomId) throws GlobalException {
		return new ResponseEntity<>(roomService.getRoomById(roomId), HttpStatus.OK);
	}

	// MAPPING METHOD 3
	// HANDLE THE REQUEST TO DELETE A ROOM FROM DATABASE USING PRIMARY KEY
	@DeleteMapping("/delete/{roomId}")
	public ResponseEntity<String> deleteRoom(@PathVariable int roomId) throws GlobalException {
		return new ResponseEntity<>(roomService.deleteRoom(roomId), HttpStatus.OK);
	}
}
