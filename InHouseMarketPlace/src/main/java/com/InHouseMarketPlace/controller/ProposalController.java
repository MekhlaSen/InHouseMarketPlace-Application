package com.InHouseMarketPlace.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.InHouseMarketPlace.DTO.ProposalDTO;
import com.InHouseMarketPlace.DTO.ProposalUpDTO;
import com.InHouseMarketPlace.entity.Proposal;
import com.InHouseMarketPlace.exception.InvalidProposalException;
import com.InHouseMarketPlace.service.ProposalService;

@RestController
public class ProposalController {
	
	@Autowired
	private ProposalService proposalService;
	
	//Adding a proposal using request method POST 
	@PostMapping("/addProposal")
	public ResponseEntity<Proposal> addProposal(@Valid @RequestBody ProposalDTO proposalDTO)
	{
		if(UserController.validUser == 1)
		{
			Proposal prop = proposalService.addProposal(proposalDTO);
		return new ResponseEntity <Proposal>(prop, HttpStatus.OK);
	}
		else 
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
}
	//Fetching all the proposals using request method GET
	@GetMapping("/getall")
	public ResponseEntity<List<Proposal>> getAllProposal() 
	{
		if(UserController.validUser == 1 )
		{
			 List<Proposal> prop =  proposalService.getAllProposal();
			 return new ResponseEntity<List<Proposal>>(prop, HttpStatus.OK);
		}
			else 
			{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		
	}
	
	//fetching all the proposals for a specific propId using request method GET
	@GetMapping("/proposal/{propId}")
	public ResponseEntity<Proposal> fetchByIdProposal(@PathVariable("propId") int propId)
	{
		if(UserController.validUser == 1 )
		{
		Proposal feprop= proposalService.getProposal(propId);
		
         if(feprop.getPropId()==0) 
         {
		throw new InvalidProposalException("No proposal with id="+ propId+ "found..");
		 }
         else {
        	 return new ResponseEntity<Proposal>(feprop, HttpStatus.OK);
              }
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		  }

}
	//Delete the proposal for a specific propId using request method DELETE
	@DeleteMapping("/deleteproposal/{propId}")
	public ResponseEntity<String> removeProposal(@PathVariable int propId )
	{
		if(UserController.validUser == 1 )
		{
		Proposal isProposalAvailable = proposalService.removeProposal(propId);
		
		return ResponseEntity.ok("Proposal with propId " +propId + " has been deleted");
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
}
