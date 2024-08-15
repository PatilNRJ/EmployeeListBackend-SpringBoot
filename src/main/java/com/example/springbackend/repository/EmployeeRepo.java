package com.example.springbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springbackend.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>{

}
