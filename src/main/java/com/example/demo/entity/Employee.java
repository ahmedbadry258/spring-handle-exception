package com.example.demo.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;


import com.example.demo.util.Converter01;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import jdk.jfr.Timestamp;

@Entity
@Table(name = "EMPLOYEE")
public class Employee implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
	@SequenceGenerator(name = "employee_seq", sequenceName = "EMPLOYEE_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private Integer id;
//	@NotEmpty(message = "Name must be not empty")
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Email(message = " Email Must be Valid")
	@Column(name = "email",unique = true)
	private String email;
	@Column(name = "active")
	@Convert(converter = Converter01.class)
	private boolean active;
//	@NotNull(message = "Password Not Null")
//	private String password;
//	@NotBlank(message = "Phone Not Blank")
//	private String phone;
//
//	@NotEmpty(message = "Age Not Empty")
//	private String age;


	public Employee() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
