package com.fapah.crud.service;

import com.fapah.crud.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> findAll();

    public Employee addEmployee(Employee employee, long departmentId);
}
