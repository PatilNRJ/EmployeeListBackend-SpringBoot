package com.example.springbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbackend.model.Employee;
import com.example.springbackend.repository.EmployeeRepo;
import com.example.springbackend.exception.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class Controller {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@GetMapping("/employees")
	public List<Employee> getAllemployee(){	
		return employeeRepo.findAll();
	}
	
	@PostMapping("/employees")
	public ResponseEntity<?> createEmployee(@RequestBody List<Employee> employeeList) {
		employeeRepo.saveAll(employeeList);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<?> getEmployeebyID(@PathVariable Long id) {
		Employee employee = employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee is not present in the DB with the ID :"+id));
				return ResponseEntity.ok(employee);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
		Employee employee = employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee is not present in the DB with the ID:"+id));
	    employee.setFirstName(employeeDetails.getFirstName());
	    employee.setEmailId(employeeDetails.getEmailId());
	    employee.setLastName(employeeDetails.getLastName());
	    Employee updatedEmployee = employeeRepo.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
		Employee employee = employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee is not present in the DB with the ID:"+id));
	    employeeRepo.delete(employee);
		return ResponseEntity.noContent().build();
	}
}
