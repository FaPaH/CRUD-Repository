package com.fapah.crud.controller;

import com.fapah.crud.exception.NullParameterException;
import com.fapah.crud.entity.Employee;
import com.fapah.crud.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody(required = true) Employee employee, @RequestParam(required = true) long departmentId) {
        try {
            return ResponseEntity.ok(employeeService.addEmployee(employee, departmentId));
        } catch (RuntimeException e) {
            throw new NullParameterException("One of the parameters is null.");
        }
    }
}
