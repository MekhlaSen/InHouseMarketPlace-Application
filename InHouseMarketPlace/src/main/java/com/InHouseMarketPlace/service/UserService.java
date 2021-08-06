package com.InHouseMarketPlace.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.InHouseMarketPlace.entity.Users;
import com.InHouseMarketPlace.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	//Logging in using userId and password	
	public boolean login(String userId, String password)
	{
		Users loggedUser=userRepository.login(userId, password);
		if(loggedUser!=null) 
		{
			return true;
		}
		else
		{
			return false;
		}
	}	
	
	//Adding User with unique userId and password
	public ResponseEntity<?> addUser(Users user)
	{
		Users u = userRepository.findById(user.getUserId()).orElse(null);
		if(Objects.nonNull(u))
		{
			return ResponseEntity.ok("User already present");
		}
		else
		{
			userRepository.save(user);
			return new ResponseEntity<String>("User added", HttpStatus.OK);
		}
	}
	
	//Updating User
	public ResponseEntity<?> editUser(Users user)
	{
		Users u = userRepository.findById(user.getUserId()).orElse(null);
		if(Objects.isNull(u))
		{
			
			return ResponseEntity.ok("User not present");
		}
		else
		{
			userRepository.save(user);
			return new ResponseEntity<String>("User Updated.", HttpStatus.OK); 
		}
	}
	
	//Deleting user using particular userId
	public Users deleteUser(String id) 
	{
		
		userRepository.deleteById(id);
		return null;
		
	}

}
