package com.ghost.ghost.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ghost.ghost.apiResponse.ApiResponse;
import com.ghost.ghost.dto.EmployeeDto;
import com.ghost.ghost.service.EmployeeService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    private EmployeeService employeeService;

    @PostMapping("/employee")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto saveEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(saveEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId) {
        EmployeeDto getSingleEmployee = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(getSingleEmployee);
    }

    @GetMapping("/employee")
    public ResponseEntity<List<EmployeeDto>> getAllEmployee() {
        List<EmployeeDto> getAllEmployees = employeeService.getAllEmployees();
        return ResponseEntity.ok(getAllEmployees);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<EmployeeDto> updateEmployeeById(
            @PathVariable("id") Long employeeId,
            @RequestBody EmployeeDto updateEmployeeDto) {
        EmployeeDto employeeDto = employeeService.updateEmployee(employeeId, updateEmployeeDto);

        return ResponseEntity.ok(employeeDto);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<ApiResponse> deleteEmployeeById(@PathVariable("id") Long employeeId) {
        employeeService.deleteEmployeeById(employeeId);
        ApiResponse response = new ApiResponse("Delete Employee Successfully!");
        return ResponseEntity.ok(response);
    }

}
