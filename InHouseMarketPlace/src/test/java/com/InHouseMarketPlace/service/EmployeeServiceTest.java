package com.InHouseMarketPlace.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.InHouseMarketPlace.entity.Employee;
import com.InHouseMarketPlace.repository.EmployeeRepository;
import com.InHouseMarketPlace.repository.UserRepository;

@SpringBootTest
class EmployeeServiceTest {
	
	@Autowired
	private EmployeeService employeeService;
	
	@MockBean
	private EmployeeRepository employeeRepository;
	
	@BeforeEach
	void setUp() throws Exception
	{
		Employee e = Employee.builder().empId(100).empName("Mekhla").location("HYD").deptName("EE").build();

		Mockito.when(employeeRepository.findByEmployeeId(100)).thenReturn(e);
		
	}

	@Test
	@DisplayName("Testing the Employee by empId")
	void findByEmployeeIdTest() 
	{
		int empId = 100;
		
		Employee found = employeeService.getEmployee(empId);
		
		assertEquals(empId, found.getEmpId());
	}

}
