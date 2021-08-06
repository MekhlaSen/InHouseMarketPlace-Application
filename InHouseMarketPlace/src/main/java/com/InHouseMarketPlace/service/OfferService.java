package com.InHouseMarketPlace.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.InHouseMarketPlace.DTO.OfferDTO;
import com.InHouseMarketPlace.DTO.OfferUpDTO;
import com.InHouseMarketPlace.entity.Employee;
import com.InHouseMarketPlace.entity.Offer;
import com.InHouseMarketPlace.entity.Proposal;
import com.InHouseMarketPlace.entity.Resources;
import com.InHouseMarketPlace.exception.InvalidEmployeeException;
import com.InHouseMarketPlace.exception.InvalidOfferException;
import com.InHouseMarketPlace.exception.InvalidProposalException;
import com.InHouseMarketPlace.exception.InvalidResourceException;
import com.InHouseMarketPlace.repository.EmployeeRepository;
import com.InHouseMarketPlace.repository.OfferRepository;
import com.InHouseMarketPlace.repository.ProposalRepository;
import com.InHouseMarketPlace.repository.ResourceRepository;

@Service
public class OfferService {
	@Autowired
	private OfferRepository offerRepository;
	@Autowired
	private ProposalRepository proposalRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private ResourceRepository resourceRepository;
	
	//Adding a offer for a particular resource
	public Offer addOffer(OfferDTO offer) 
	{
		Employee e = employeeRepository.findById(offer.getEmpId()).orElse(null);
		if(Objects.isNull(e))
		{
			throw new InvalidEmployeeException("Employee not found..");
		}
		
	    Offer o = new Offer();
	    o.setEmployee(e);
	    o.setTitle(offer.getTitle());
	    o.setDescription(offer.getDescription());
	    o.setCategory(offer.getCategory());
	    o.setIdate(offer.getIdate());
	    o.setType(offer.getType());
	    o.setPrice(offer.getPrice());
	    o.setOfferId(offer.getOfferId());
	    o.setIsAvailable(offer.getIsAvailable());
	    o.setAvailableUpto(offer.getAvailableUpto());
	    
		return offerRepository.save(o);
	}
	
	//Fetching a offer by using its respective resource Id
	public Offer findOfferById(int resId) {
		Resources res = offerRepository.findById(resId).orElse(null);
		if (Objects.isNull(res)) {
			throw new InvalidResourceException("Resources not found..");
		} else {
			return (Offer) res;
		}
	}
	
	//Updating an existing offer for a particular employee Id and resource Id
	public Offer editOffer(OfferUpDTO offer) {
		Employee e = employeeRepository.findById(offer.getEmpId()).orElse(null);
		Resources r= resourceRepository.findById(offer.getResId()).orElse(null);
		if(Objects.isNull(e)) {
			throw new InvalidEmployeeException("Employee not found..");
		}
		if(Objects.isNull(r)) {
			throw new InvalidResourceException("Resource not found..");
		}
		Offer o = new Offer();
		o.setEmployee(e);
		o.setResId(offer.getResId());
		o.setTitle(offer.getTitle());
	    o.setDescription(offer.getDescription());
	    o.setCategory(offer.getCategory());
	    o.setIdate(offer.getIdate());
	    o.setType(offer.getType());
	    o.setPrice(offer.getPrice());
	    o.setOfferId(offer.getOfferId());
	    o.setIsAvailable(offer.getIsAvailable());
	    o.setAvailableUpto(offer.getAvailableUpto());
	    
	    return offerRepository.save(o);
	    
	}
	
	//Fetching a offer for a respective offer Id
	public Offer getOffer(int offerId) {
		return offerRepository.findByOfferId(offerId) ;
	}
	
	//Deleting an existing offer for a particular Resource Id
	public Offer removeOffer(int resId) 
	{
		Offer o = offerRepository.findById(resId).orElse(null);
		if(Objects.isNull(o))
		{
			throw new InvalidOfferException("Offer not found..");
		}
		else {
			o.setEmployee(null);
			o.setProposals(null);
		
			Offer off = offerRepository.save(o);
			
			offerRepository.deleteById(off.getResId());
			return null;
		}				
		
	}
	
	//Fetching all the offers for a particular resource
	public List<Offer> getAllOffers() {
		return (List<Offer>) offerRepository.findAll();
	}
	
	//fetching all the offers using its resource category and type
	public List<Offer> getAllOffers(String category, String type) {
		return (List<Offer>) offerRepository.findByCategoryAndType(category,type);
	}

}