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

import com.InHouseMarketPlace.DTO.OfferDTO;
import com.InHouseMarketPlace.DTO.OfferUpDTO;
import com.InHouseMarketPlace.entity.Offer;
import com.InHouseMarketPlace.exception.InvalidOfferException;
import com.InHouseMarketPlace.service.OfferService;
import com.InHouseMarketPlace.service.ResourceService;

@RestController
public class OfferController {
	
	@Autowired
	private OfferService offerService;
	@Autowired
	private ResourceService resourceService;
	
	//adding a offer using request method POST 
	@PostMapping("/addoffer")
	public ResponseEntity<Offer> addOffer(@RequestBody OfferDTO offerDTO) 
	{
		if(UserController.validUser == 1 ) 
		{
			Offer off = offerService.addOffer(offerDTO);
		    return new ResponseEntity <Offer>(off, HttpStatus.OK);
	    }
		else 
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	 //updating an existing offer using request method PUT
	@PutMapping("/updateoffer")
	public ResponseEntity<Offer> editOffer(@RequestBody OfferUpDTO offer) 
	{
		if(UserController.validUser == 1)
		{
			Offer off = offerService.editOffer(offer);
		return new ResponseEntity <Offer>(off, HttpStatus.OK);
	}
		else 
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	 //Fetching all the offers for its respective resource Id using request method GET 
	@GetMapping("/getoffer/{resId}")
	public ResponseEntity<Offer> getResourceById(@PathVariable int resId) {
		if (UserController.validUser == 1) {
			Offer o = offerService.findOfferById(resId);
			return new ResponseEntity<Offer>(o, HttpStatus.OK);
		}
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		
	}
	
	 //deleting a particular offer by its respective resource Id
	@DeleteMapping("/deleteoffer/{resId}")
	public ResponseEntity<?> deleteOfferById(@PathVariable int resId )
	{
		if(UserController.validUser == 1)
		{
			offerService.removeOffer(resId);
			
			return  ResponseEntity.ok("OfferId " + resId + " has been deleted");

		}
		else 
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	//fetching all the offers using request method GET 
	@GetMapping("/getalloffers")
	public ResponseEntity<List<Offer>> getAllOffers()
	{
		if(UserController.validUser == 1)
		{
			List<Offer> off = offerService.getAllOffers();
		    return new ResponseEntity<List<Offer>>(off, HttpStatus.OK);
	    }
		else 
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	//Fetching all the offers for its respective resource category and type using request method GET
	@GetMapping("/getalloffers/{category}/{type}")
	public ResponseEntity<List<Offer>> getAllOffers(@PathVariable String category,@PathVariable String type)
	{
		if(UserController.validUser == 1)
		{
			List<Offer> off = offerService.getAllOffers(category, type);
		    return new ResponseEntity<List<Offer>>(off, HttpStatus.OK);
	    }
		else 
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}