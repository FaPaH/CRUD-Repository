package com.fapah.crud.controller;

import com.fapah.crud.exception.NullParameterException;
import com.fapah.crud.entity.Employee;
import com.fapah.crud.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService employeeService;

    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        log.info("Getting all employees in EmployeeController");
        return ResponseEntity.ok(employeeService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody(required = true) Employee employee, @RequestParam(required = true) long departmentId) {
        try {
            log.info("Adding employee {} in addEmployee in EmployeeController with departmentId {}", employee, departmentId);
            return ResponseEntity.ok(employeeService.addEmployee(employee, departmentId));
        } catch (DataAccessException e) {
            log.debug("Error while adding employee {} with departmentId {}", employee, departmentId, e);
            throw new NullParameterException("One of the parameters is null.");
        }
    }
}
