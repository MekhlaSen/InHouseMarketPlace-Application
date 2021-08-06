package com.InHouseMarketPlace.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	@Id
	private int empId;
	private String empName;
	private String deptName;
	private String location;
	
	@OneToOne(targetEntity=Users.class,cascade=CascadeType.ALL)
	@JoinColumn(name="userId",referencedColumnName="USER_ID")
	private Users user;
	
}