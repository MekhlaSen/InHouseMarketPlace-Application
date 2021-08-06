package com.InHouseMarketPlace.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.InHouseMarketPlace.entity.Users;
import com.InHouseMarketPlace.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	static int validUser = 0;
	static int admin = 0;
	
	//Admin login using admin Id and Password by request method GET
	  @GetMapping("/admin/{adminId}/{adminPassword}") 
	  public ResponseEntity<String> admin(@PathVariable("adminId") String adminId, @PathVariable("adminPassword") String adminPassword)
	  { 
		  boolean value = userService.login(adminId, adminPassword);
		  if(value == true)
		  {
			 validUser = 1;
			  if(adminId.equals("adminId") && adminPassword.equals("adminPassword"))
			  {
				  admin = 1;
				  return new ResponseEntity<String>("Admin Login successful", HttpStatus.OK);
			  }
			  else
			  {
				  return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			  }
		  }
		  else
		  {
			  return ResponseEntity.ok("Invalid Credentials");
		  }
	  }
	 
	//User Login using user Id and Password request method GET
	  @GetMapping("/login/{userId}/{password}") 
	  public ResponseEntity<?> login(@PathVariable("userId") String userId, @PathVariable("password") String password) 
	  { 
		  boolean value = userService.login(userId, password);
		  if(value == true)
		  {
			  validUser = 1;
			  return ResponseEntity.ok("Logged In");
		 }
		  else
		  {
			  return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		  }
	  }
	
	//Adding user by request method GET
	  @PostMapping("/addUser") 
	  public ResponseEntity<?> addUser(@RequestBody Users user) 
	  { 
		 if(admin == 1)
		 {
			return userService.addUser(user);
		 }	  
		 else
		  {
			  return ResponseEntity.ok("Please Login first!");
		  }
	  }
	 
	  //Updating User by request method PUT
	@PutMapping("/updateUser")
	public ResponseEntity<?> update(@RequestBody Users user)
	{
		if(admin == 1)
		{
			return  userService.editUser(user);
		 }
		  
		 else
		  {
			  return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		  }
		
	}
	
	//Deleting User for a particular userId using request method DELETE
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<?> delete(@PathVariable String userId)
	{
		if(admin == 1)
		{
			Users user1 = userService.deleteUser(userId);
			//return new ResponseEntity<Users>(user1, HttpStatus.OK);
			return ResponseEntity.ok("User with user Id " +userId +" has been deleted");
		}
		else
		{
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
