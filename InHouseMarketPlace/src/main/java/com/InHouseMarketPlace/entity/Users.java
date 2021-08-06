package com.InHouseMarketPlace.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="USERS")
public class Users {

	@Id
	@Column(name = "USER_ID")
	private String userId;
	private String password;
		
}
