
package com.hostelms.serviceimpl;

import java.util.List;

import com.hostelms.exception.GlobalException;
import com.hostelms.model.Room;
import com.hostelms.modeldto.RoomDTO;
import com.hostelms.repository.RoomRepository;
import com.hostelms.service.RoomService;
import com.hostelms.util.ValueMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepository roomRepo;

	@Override
	public String addRoom(RoomDTO room) {
	
		Room _room = ValueMapper.dataTransferToRoom(room);
		roomRepo.save(_room);
		return "Room added";
	}

	@Override
	public String deleteRoom(int roomId) throws GlobalException {
		
		Room _room = roomRepo.findByRoomId(roomId);
		if (_room != null) {
			roomRepo.deleteById(roomId);
			return _room.getRoomName() + " Deleted";
		} else
			throw new GlobalException("Room not found!!!");
	}

	
	@Override
	public Room getRoomById(int roomId) throws GlobalException {
		
		Room _room = roomRepo.findByRoomId(roomId);
		if (_room != null)
			return _room;
		else
			throw new GlobalException("Room not found!!!");
	}

	
	@Override
	public List<Room> getAllRoom() throws GlobalException {
		
		List<Room> _roomList = roomRepo.findAll();
		if (_roomList.isEmpty())
			throw new GlobalException("Table is empty \nNo Room Exist!!!");
		else
			return _roomList;
	}

}
