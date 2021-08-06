package com.InHouseMarketPlace.DTO;

import java.time.LocalDate;
import java.util.List;

import com.InHouseMarketPlace.entity.Proposal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferDTO {

	private int offerId = (int) Math.random();
	private String title;
	private String description;
	private String category;
	private String idate;
	private String type;	
	private double price;
	private int empId;
	private String isAvailable;
	private LocalDate availableUpto;
	
}
