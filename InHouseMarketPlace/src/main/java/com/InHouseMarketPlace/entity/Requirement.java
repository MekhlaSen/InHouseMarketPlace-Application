package com.InHouseMarketPlace.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Requirement extends Resources {
	
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private int reqId;
	private String isFulfilled;
	private LocalDate fulfilledOn;
	
	@OneToMany(targetEntity=Requirement.class,cascade=CascadeType.MERGE)
	@JoinColumn(name = "Require_Prop" )
	private List<Proposal> proposals;
	
	public void addProposal(Proposal proposal)
	{
		proposals.add(proposal);
	}
}