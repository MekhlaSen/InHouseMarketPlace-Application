package com.InHouseMarketPlace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.InHouseMarketPlace.entity.Proposal;


@Repository
public interface ProposalRepository extends JpaRepository<Proposal,Integer>
{
	@Query("select p from Proposal p where propId=?1 ")
	public Proposal findByProposalId(int propId);
}
