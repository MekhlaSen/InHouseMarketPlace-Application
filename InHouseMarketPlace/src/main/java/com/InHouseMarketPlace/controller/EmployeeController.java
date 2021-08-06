package com.InHouseMarketPlace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.InHouseMarketPlace.entity.Employee;
import com.InHouseMarketPlace.service.EmployeeService;

@RestController
public class EmployeeController {


	@Autowired
	private EmployeeService employeeservice;
	
	//Adding employee by an admin using request method POST 
	@PostMapping("/addemployee")
	public ResponseEntity<?> saveEmployee(@RequestBody Employee emp)
	{
		if(UserController.admin == 1) 
		{
		
			return employeeservice.addEmployee(emp);
		 
		}
		else {
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	 //Updating an existing employee details by an admin using request method PUT
	  @PutMapping("/updateEmployee")
	  public  ResponseEntity<?> updateEmployee(@RequestBody Employee emp)
	  {
		  if(UserController.admin == 1)
		  {
			  return employeeservice.editEmployee(emp);
			  
		  }
		  else
		  {
			  return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		  }
		 
		 
	  }
	  
	  //Fetching employee details by empId using request method GET
	  @GetMapping("/employebyid/{empId}")
	   public ResponseEntity<Employee> getEmployeeById(@PathVariable int empId)
	  {
		  if(UserController.admin == 1) 
		  {
		    Employee fetchedEmployee = employeeservice.getEmployee(empId);
		    if(fetchedEmployee.getEmpId()==0)
		     {
			     System.out.println("Employee Not found!");
		     }
		  
			return new ResponseEntity<Employee>(fetchedEmployee,HttpStatus.OK);
		  }
		  else
		  {
			  return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		  }
     
}
	  
}