package com.InHouseMarketPlace.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
public class Offer extends Resources {

	private int offerId;
	private String isAvailable;
	private LocalDate availableUpto;
	
	@OneToMany(targetEntity=Offer.class,cascade=CascadeType.MERGE)
	@JoinColumn(name = "Offer_Prop" )
	private List<Proposal> proposals;
	
	public void addProposal(Proposal proposal)
	{
		proposals.add(proposal);
	}
}
