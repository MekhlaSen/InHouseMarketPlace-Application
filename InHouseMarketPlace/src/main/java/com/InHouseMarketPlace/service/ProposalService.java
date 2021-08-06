package com.InHouseMarketPlace.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.InHouseMarketPlace.DTO.ProposalDTO;
import com.InHouseMarketPlace.DTO.ProposalUpDTO;
import com.InHouseMarketPlace.entity.Employee;
import com.InHouseMarketPlace.entity.Proposal;
import com.InHouseMarketPlace.entity.Resources;
import com.InHouseMarketPlace.exception.InvalidEmployeeException;
import com.InHouseMarketPlace.exception.InvalidProposalException;
import com.InHouseMarketPlace.exception.InvalidResourceException;
import com.InHouseMarketPlace.repository.EmployeeRepository;
import com.InHouseMarketPlace.repository.ProposalRepository;
import com.InHouseMarketPlace.repository.ResourceRepository;


@Service
public class ProposalService {

	@Autowired
	private ProposalRepository proposalRepository;
	@Autowired
	private ResourceRepository resourceRepository;
	@Autowired
	private EmployeeRepository employeeRepository;

	//Adding a proposal for a particular resource
	public Proposal addProposal(ProposalDTO prop) 
	{
		Employee e = employeeRepository.findById(prop.getEmpId()).orElse(null);
		if(Objects.isNull(e))
		{
			throw new InvalidEmployeeException("Employee not found..");
		}
		Resources r = resourceRepository.findById(prop.getResId()).orElse(null);
		if(Objects.isNull(r))
		{
			throw new InvalidResourceException("Resource not found..");
		}
		Proposal p = new Proposal();
		
		p.setEmployee(e);
		p.setResource(r);
		p.setAmount(prop.getAmount());
		p.setAcceptedOn(prop.getAcceptedOn());
		p.setIsAccepted(prop.getIsAccepted());
		p.setProposalDate(prop.getProposalDate());
		p.setPropId(prop.getPropId());
		p.setProposal(prop.getProposal());
		
		return proposalRepository.save(p);
		
	}
	
	//Updating the proposal for a specific propId
	public Proposal getProposal(int propId)  {
		return proposalRepository.findById(propId).orElse(new Proposal());

	}
	
	//Removing the proposal for a specific propId
	public Proposal removeProposal(int propId)
	{

		Proposal p = proposalRepository.findById(propId).orElse(null);
		if(Objects.isNull(p))
		{
			throw new InvalidProposalException("Proposal not found..");
		}
		else {
			p.setEmployee(null);
			p.setResource(null);
			Proposal pro = proposalRepository.save(p);
		
		 proposalRepository.deleteById(propId);
		 return null ;
		}
	}

	//Finding a proposal for a specific propId
	public List<Proposal> getAllProposal() {
		return proposalRepository.findAll();

	}
}