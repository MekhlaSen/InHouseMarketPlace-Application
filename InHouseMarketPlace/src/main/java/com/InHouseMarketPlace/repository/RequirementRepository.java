package com.InHouseMarketPlace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.InHouseMarketPlace.entity.Requirement;
@Repository
public interface RequirementRepository extends JpaRepository<Requirement,Integer>
{

	 List<Requirement> findByCategoryAndType(String category, String type);
	
	@Query("select r from Requirement r where reqId=?1")
	public Requirement findByRequirementId(int reqId);

}