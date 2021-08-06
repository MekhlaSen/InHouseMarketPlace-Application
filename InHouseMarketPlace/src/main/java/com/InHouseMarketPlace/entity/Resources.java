package com.InHouseMarketPlace.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Resources {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int resId;
	private String title;
	private String description;
	private String category;
	private String idate;
	private String type;	
	private double price;

	@ManyToOne(targetEntity=Employee.class,cascade=CascadeType.ALL)
	@JoinColumn(name = "EmpId" ) 
	private Employee employee;
}
