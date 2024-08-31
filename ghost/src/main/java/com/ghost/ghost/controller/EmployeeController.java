package com.ghost.ghost.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ghost.ghost.dto.EmployeeDto;
import com.ghost.ghost.service.EmployeeService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    private EmployeeService employeeService;

    @PostMapping("/employee")
    public ResponseEntity<EmployeeDto> createEmployee(EmployeeDto employeeDto) {
        EmployeeDto savEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savEmployee, HttpStatus.CREATED);
    }

}
