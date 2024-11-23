package com.example.demo.services;

import java.util.List;

import com.example.demo.errors.FieldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entity.Employee;
import com.example.demo.errors.EmployeeException;
import com.example.demo.errors.RecordNotFoundException;
import com.example.demo.repos.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> findAll() throws Exception{
		 List<Employee> all=	employeeRepository.findAll();
		 if (all.isEmpty()) {
			 throw new EmployeeException("No Data Found");
		 }
		 return all ;
	}
	public Employee addEmployee( Employee employee) throws EmployeeException {
		if(employee.getFirstName() == null )throw new FieldException("Invalid Input","First name","First name is null");

		if(employee.getLastName() == null)throw new IllegalArgumentException("Last name is null");
		if(employee.getEmail() == null)throw new IllegalArgumentException("Email is null");

		return employeeRepository.save(employee);
	}
	
	public Employee getEmployeeById(int id) {
		return employeeRepository.findById(id)
				.orElseThrow(()-> new RecordNotFoundException("Employee Not Found With ID :"+id));
	}
}
