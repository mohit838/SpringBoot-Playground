package com.ghost.ghost.service.employeeImpl;

import org.springframework.stereotype.Service;

import com.ghost.ghost.dto.EmployeeDto;
import com.ghost.ghost.entity.Employee;
import com.ghost.ghost.mapper.EmployeeMapper;
import com.ghost.ghost.repository.EmployeeRepository;
import com.ghost.ghost.service.EmployeeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

}
