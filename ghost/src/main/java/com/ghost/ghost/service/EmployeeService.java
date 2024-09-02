package com.ghost.ghost.service;

import java.util.List;

import com.ghost.ghost.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long EmployeeId);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployeeDto);

    void deleteEmployeeById(Long employeeId);

}
