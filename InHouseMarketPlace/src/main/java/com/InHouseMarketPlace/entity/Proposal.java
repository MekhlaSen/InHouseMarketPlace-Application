package com.InHouseMarketPlace.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Proposal {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int propId;
	private String proposal;
	private double amount;
	private LocalDate proposalDate;
	private String isAccepted;
	private LocalDate acceptedOn;
	 
	@JsonIgnoreProperties
	  @ManyToOne(targetEntity=Resources.class,cascade=CascadeType.ALL)
	  @JoinColumn(name = "Proposal_Res" ) 
	  private Resources resource;
	 
	@JsonIgnoreProperties
	  @ManyToOne(targetEntity=Employee.class,cascade=CascadeType.ALL)
	  @JoinColumn(name = "Proposal_EmpId" ) 
	  private Employee employee;
	 
}

	