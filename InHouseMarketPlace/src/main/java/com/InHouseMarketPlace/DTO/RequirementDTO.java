package com.InHouseMarketPlace.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequirementDTO {

	private String title;
	private String description;
	private String category;
	private String idate;
	private String type;	
	private String price;
	private int empId;
	private String isFulfilled;
	private LocalDate fulfilledOn;
	
}
