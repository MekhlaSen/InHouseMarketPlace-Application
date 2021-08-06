package com.InHouseMarketPlace.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProposalUpDTO {
	
	//private int propId;
	private String proposal;
	private double amount;
	private LocalDate proposalDate;
	private String isAccepted;
	private LocalDate acceptedOn;
	private int resId;
	private int empId;

}
