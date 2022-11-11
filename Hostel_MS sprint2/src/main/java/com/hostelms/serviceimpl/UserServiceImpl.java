
package com.hostelms.serviceimpl;

import java.util.List;

import com.hostelms.exception.GlobalException;
import com.hostelms.model.User;
import com.hostelms.modeldto.UserDTO;
import com.hostelms.repository.UserRepository;
import com.hostelms.service.UserService;
import com.hostelms.util.ValueMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public String addUser(UserDTO user) throws GlobalException {
		
		User _user = userRepo.findByUserId(user.getUserId());
		if (_user != null)
			throw new GlobalException("User Already Exist!!!");
		else {
			_user = ValueMapper.dataTransferToUser(user);
			userRepo.save(_user);
		}
		return _user.getFirstName() + " " + _user.getLastName() + " added.";
	}

	
	@Override
	public String deleteUser(int userId) throws GlobalException {
		
		User _user = userRepo.findByUserId(userId);
		if (_user != null) {
			userRepo.deleteById(userId);
			return _user.getFirstName() + " " + _user.getLastName() + " deleted.";
		} else
			throw new GlobalException("User does not exist.");
	}

	@Override
	public User getUserById(int userId) throws GlobalException {
		User _user = userRepo.findByUserId(userId);
		if (_user != null)
			return _user;
		else
			throw new GlobalException("User does not exist");
	}

	
	@Override
	public List<User> getAllUser() throws GlobalException {
		
		List<User> _userList = userRepo.findAll();
		if (_userList.isEmpty())
			throw new GlobalException("Empty Table");
		return _userList;
	}

	@Override
	public User updateUser(UserDTO user) {
		
		User _user = ValueMapper.dataTransferToUser(user);
		return userRepo.save(_user);
	}

	
	@Override
	public List<User> sortUser(String fields) {
		
		return userRepo.findAll(Sort.by(Direction.DESC, fields));
	}

	@Override
	public String updateRent(int userId, int userRent) throws GlobalException {
		
		User _user = userRepo.findByUserId(userId);
		if (_user != null) {
			int status = userRepo.updateUserRent(userId, userRent);
			return "Rent Updated";
		} else
			throw new GlobalException("User not found!!!");
	}

	@Override
	public String allotRoom(int roomId, int userId) throws GlobalException {
		
		List<User> _userList = userRepo.roomStatus(roomId);
		if (_userList.size() < 4) {
			userRepo.allotUserRoom(roomId, userId);
			User _user = userRepo.findByUserId(userId);
			return roomId + " Room Alloted to User " + _user.getFirstName() + " " + _user.getLastName();
		} else
			throw new GlobalException("Room is already full!!!");
	}

	
	@Override
	public String login(int userId, String userName, String userPassword) throws GlobalException {
		
		User _user = userRepo.findByUserId(userId);
		if (_user != null)
			if (_user.getUserName().equals(userName))
				if (_user.getUserPassword().equals(userPassword))
					return "Login Successfull. \nWelcome " + _user.getFirstName() + " " + _user.getLastName();
				else
					throw new GlobalException("Wrong Password!!!");
			else
				throw new GlobalException("Wrong Username!!!");
		else
			throw new GlobalException("User not found!!!");
	}

	@Override
	public String updateEmail(int userId, String email) throws GlobalException {
		
		User _user = userRepo.findByUserId(userId);
		if (_user != null) {
			int status = userRepo.updateUserEmail(userId, email);
			return "Email Updated";
		} else
			throw new GlobalException("User not found!!!");
	}

	
	@Override
	public String updateContact(int userId, String contactNo) throws GlobalException {
		
		User _user = userRepo.findByUserId(userId);
		if (_user != null) {
			int status = userRepo.updateUserContact(userId, contactNo);
			return "Phone no Updated";
		} else
			throw new GlobalException("User not found!!!");
	}

}
