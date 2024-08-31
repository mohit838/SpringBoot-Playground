package com.ghost.ghost.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ghost.ghost.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
