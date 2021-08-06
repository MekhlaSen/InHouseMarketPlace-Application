package com.InHouseMarketPlace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.InHouseMarketPlace.entity.Resources;

@Repository
public interface ResourceRepository extends JpaRepository<Resources, Integer> {

	//@Query("select r from Resources r where category=?1 and type=?2")
	//public List<Resources> findByCategoryAndtype(String category, String type);
	
	@Query("select r from Resources r where EmpId=?1")
	public List<Resources> findByEmpId(int empId);
	
	@Query("select r from Resources r")
	public List<Resources> findByResource();
}
