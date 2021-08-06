package com.InHouseMarketPlace.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.InHouseMarketPlace.DTO.RequirementDTO;
import com.InHouseMarketPlace.DTO.RequirementUpDTO;
import com.InHouseMarketPlace.entity.Employee;
import com.InHouseMarketPlace.entity.Offer;
import com.InHouseMarketPlace.entity.Proposal;
import com.InHouseMarketPlace.entity.Requirement;
import com.InHouseMarketPlace.entity.Resources;
import com.InHouseMarketPlace.exception.InvalidEmployeeException;
import com.InHouseMarketPlace.exception.InvalidOfferException;
import com.InHouseMarketPlace.exception.InvalidProposalException;
import com.InHouseMarketPlace.exception.InvalidRequirementException;
import com.InHouseMarketPlace.exception.InvalidResourceException;
import com.InHouseMarketPlace.repository.EmployeeRepository;
import com.InHouseMarketPlace.repository.ProposalRepository;
import com.InHouseMarketPlace.repository.RequirementRepository;
import com.InHouseMarketPlace.repository.ResourceRepository;


@Service
public class RequirementService {

	@Autowired
	private  RequirementRepository requirementRepository;
	@Autowired
	private ProposalRepository proposalRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private ResourceRepository resourceRepository;
	
	 
	//Adding a Requirement for a particular resource
	public Requirement addRequirement(RequirementDTO req)
	{
		Employee e = employeeRepository.findById(req.getEmpId()).orElse(null);
		if(Objects.isNull(e))
		{
			throw new InvalidEmployeeException("Employee not found..");
		}
		Requirement r = new Requirement();
		r.setEmployee(e);
		r.setTitle(req.getTitle());
		r.setDescription(req.getDescription());
		r.setCategory(req.getCategory());
		r.setIdate(req.getIdate());
		r.setType(req.getType());
		r.setPrice(Double.parseDouble(req.getPrice().trim()));
		r.setIsFulfilled(req.getIsFulfilled());
		r.setFulfilledOn(req.getFulfilledOn());
		
		return requirementRepository.save(r);
	}


	//Updating an existing Requirement for a particular employee Id and resource Id
	public Requirement editRequirement(RequirementUpDTO req) 
	{
		Employee e = employeeRepository.findById(req.getEmpId()).orElse(null);
		Resources r= resourceRepository.findById(req.getResId()).orElse(null);
		if(Objects.isNull(e)) {
			throw new InvalidEmployeeException("Employee not found..");
		}
		if(Objects.isNull(r)) {
			throw new InvalidResourceException("Resource not found..");
		}
		Requirement re = new Requirement();
		re.setEmployee(e);
		re.setResId(req.getResId());
		re.setTitle(req.getTitle());
		re.setDescription(req.getDescription());
		re.setCategory(req.getCategory());
		re.setIdate(req.getIdate());
		re.setType(req.getType());
		re.setPrice(req.getPrice());
		re.setReqId(req.getReqId());
		re.setIsFulfilled(req.getIsFulfilled());
		re.setFulfilledOn(req.getFulfilledOn());
		
		 return requirementRepository.save(re);
	}
	
	//Fetching a requirement by using its respective resource Id 
	public Requirement getRequirement(int reqId)  
	{
		return requirementRepository.findById(reqId).orElse(new Requirement());
	}
	
	
	//Deleting an existing Requirement for a particular Resource Id
	public Requirement removeRequirement(int reqId) 
	{
		Requirement r = requirementRepository.findById(reqId).orElse(null);
		if(Objects.isNull(r))
		{
			throw new InvalidRequirementException("Requirement not found..");
		}
		else {
			r.setEmployee(null);
			r.setProposals(null);
		
			Requirement re = requirementRepository.save(r);
			
			requirementRepository.deleteById(r.getResId());
			return null;
		}				
		
	}
	
	//Fetching all the requirements for a particular resource		
	public List<Requirement> getAllRequirements() {
		return (List<Requirement>) requirementRepository.findAll();
	}
	
	//fetching all the requirements using its resource category and type
    public List<Requirement> getAllRequirements(String category, String type) {
    	return (List<Requirement>) requirementRepository.findByCategoryAndType(category, type);
    }
    
}