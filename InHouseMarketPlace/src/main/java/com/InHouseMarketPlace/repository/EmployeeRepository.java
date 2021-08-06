package com.InHouseMarketPlace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.InHouseMarketPlace.entity.Employee;
import com.InHouseMarketPlace.entity.Offer;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>{


	@Query("select e from Employee e where empId=?1")
	public Employee findByEmployeeId(int EmpId);

	
}