package com.InHouseMarketPlace.controller;

import java.util.List;

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

import com.InHouseMarketPlace.DTO.RequirementDTO;
import com.InHouseMarketPlace.DTO.RequirementUpDTO;
import com.InHouseMarketPlace.entity.Offer;
import com.InHouseMarketPlace.entity.Requirement;
import com.InHouseMarketPlace.exception.InvalidRequirementException;
import com.InHouseMarketPlace.service.RequirementService;
import com.InHouseMarketPlace.service.ResourceService;

@RestController
public class RequirementController {

	@Autowired
	private RequirementService requirementService;
	@Autowired
	private ResourceService resourceService;
	
	  //adding a requirement using request method POST 
	@PostMapping("/addRequirement")
	public ResponseEntity<Requirement> addRequirement(@RequestBody RequirementDTO requirementDTO)
	{
		if(UserController.validUser == 1)
		{
			Requirement r = requirementService.addRequirement(requirementDTO);
			return new ResponseEntity <Requirement>(r, HttpStatus.OK);
		}
		else 
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	  //updating an existing requirement using request method PUT
	@PutMapping("/updatereq")
	public ResponseEntity<Requirement> updateRequirement(@RequestBody RequirementUpDTO req)
	{
		if(UserController.validUser == 1)
		{
			Requirement r = requirementService.editRequirement(req);
			return new ResponseEntity <Requirement>(r, HttpStatus.OK);
		}
		else 
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	
	  //fetching all the requirements using request method GET 
	@GetMapping("/getallreqs")
	public ResponseEntity<List<Requirement>> findAllRequirements()
	{
		if(UserController.validUser == 1)
		{
			List<Requirement> re = requirementService.getAllRequirements();
			return new ResponseEntity<List<Requirement>>(re, HttpStatus.OK);
	    }
		else 
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	  //Fetching all the requirements for its respective resource Id using request method GET
	@GetMapping("/getreq/{resId}")
	public ResponseEntity<Requirement> getResourceById(@PathVariable int resId)
	{
		if (UserController.validUser == 1) {
			Requirement re = requirementService.getRequirement(resId);
			return new ResponseEntity<Requirement>(re, HttpStatus.OK);
		}
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
	}
	
	  //Fetching all the requirements for its respective resource category and type using request method GET
	@GetMapping("/getallrequirements/{category}/{type}")
	public ResponseEntity<List<Requirement>> getAllRequirements(@PathVariable String category,@PathVariable String type)
	{
		if(UserController.validUser == 1)
		{
			List<Requirement> re = requirementService.getAllRequirements(category, type);
		    return new ResponseEntity<List<Requirement>>(re, HttpStatus.OK);
	    }
		else 
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	  //deleting a particular requirement by its respective resource Id
	@DeleteMapping("/deletereq/{resId}")
	public ResponseEntity<?> deleteRequirement(@PathVariable int resId )
	{
		if(UserController.validUser == 1)
		{
			requirementService.removeRequirement(resId);
			
			return  ResponseEntity.ok("RequirementId " + resId + " has been deleted");

		}
		else 
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}
}