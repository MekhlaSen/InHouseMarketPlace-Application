package com.InHouseMarketPlace.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.InHouseMarketPlace.entity.Proposal;
import com.InHouseMarketPlace.entity.Resources;
import com.InHouseMarketPlace.service.ResourceService;

@RestController
public class ResourceController {

	@Autowired
	public ResourceService resourceService;
	
	//Getting all the resources using request method GET
	@GetMapping("/getAllResource")
	public ResponseEntity<List<Resources>> getAllResources()
	{
		if(UserController.validUser == 1 ) 
		{
			List<Resources> res = resourceService.getAllResources(); 
			return new ResponseEntity<List<Resources>>(res, HttpStatus.OK);
		}
		else 
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	//getting all resources for the specific employee Id using request method GET
	@GetMapping("/resource/{empId}")
	public ResponseEntity<List<Resources>> getAllResources(@PathVariable int empId)
	{
		if(UserController.validUser == 1 ) 
		{
			List<Resources> res = (List<Resources>) resourceService.getResourcesById(empId); 
			return new ResponseEntity<List<Resources>>(res, HttpStatus.OK);
		}
		else 
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
}
}
