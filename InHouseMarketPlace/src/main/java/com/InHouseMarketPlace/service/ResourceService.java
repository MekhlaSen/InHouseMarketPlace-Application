package com.InHouseMarketPlace.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.InHouseMarketPlace.entity.Resources;
import com.InHouseMarketPlace.exception.InvalidResourceException;
import com.InHouseMarketPlace.repository.ResourceRepository;

@Service
public class ResourceService {

	@Autowired
	public ResourceRepository resourceRepository;

	
	//Fetching all resources available
	public List<Resources> getAllResources()
	{
		List<Resources> l = resourceRepository.findByResource();
		return l;
	}
	
	//Fetching all resources by its respective employee Id
	public List<Resources> getResourcesById(int empId)
	{
		List<Resources> l =  resourceRepository.findAll();
		List<Resources> result = new ArrayList<Resources>();
		for(int i = 0; i<l.size(); i++)
		{
			if(l.get(i).getEmployee().getEmpId()== empId)
			{
				result.add(l.get(i));
			}
		}
		return result;
	}
	
}