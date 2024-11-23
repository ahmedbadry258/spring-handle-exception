package com.example.demo.controller;



import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Employee;
import com.example.demo.errors.EmployeeException;
import com.example.demo.services.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping
	public ResponseEntity<Employee> findAll() throws Exception{
		return new ResponseEntity(employeeService.findAll(), HttpStatus.OK);
		
	}
	@PostMapping
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) throws EmployeeException{
		return new ResponseEntity(employeeService.addEmployee(employee), HttpStatus.CREATED);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") int id) {
		return new ResponseEntity(employeeService.getEmployeeById(id), HttpStatus.OK);
	}
	
	

}
