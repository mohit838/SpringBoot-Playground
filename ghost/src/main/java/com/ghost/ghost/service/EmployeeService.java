package com.ghost.ghost.service;

import com.ghost.ghost.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long EmployeeId);

}
