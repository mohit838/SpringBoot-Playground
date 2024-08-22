package com.ghost.ghostplay;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpController {

    List<Employee> employees = new ArrayList<>();

    @GetMapping("/get-all-emp")
    public ResponseWrapper getAllEmp() {
        if (employees.isEmpty()) {
            return new ResponseWrapper(employees, "No employee found!");
        } else {
            return new ResponseWrapper(employees, null);
        }
    }

    @PostMapping("/new-emp")
    public String createNewEmp(@RequestBody Employee employee) {
        employees.add(employee);
        return "Saved Successfully!";
    }

}